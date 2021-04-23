package DAO;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.ResetPasswordToken;
import Model.User;

@Repository
public class ResetPasswordToken_DAO_Imp implements ResetPasswordToken_DAO {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public void save(User user, ResetPasswordToken resetpasswordtoken) {
		try {

			Session currentSession = sessionfactory.getCurrentSession();
			Query<ResetPasswordToken> query = currentSession
					.createQuery("from ResetPasswordToken where user_id=:user_id", ResetPasswordToken.class);
			query.setParameter("user_id", user.getUser_id());
			List<ResetPasswordToken> list = query.getResultList();
			System.out.println(list);
			ResetPasswordToken token1 = new ResetPasswordToken();
			if (list.size() > 0) {
				token1 = list.get(0);
				token1.setToken(resetpasswordtoken.getToken());
				token1.setCreationDate(LocalDateTime.now());
				sessionfactory.getCurrentSession().save(token1);
			} else {
				sessionfactory.getCurrentSession().save(resetpasswordtoken);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResetPasswordToken findByToken(String token) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<ResetPasswordToken> query = currentSession.createQuery("from ResetPasswordToken where token=:token",
				ResetPasswordToken.class);
		query.setParameter("token", token);
		List<ResetPasswordToken> list = query.getResultList();
		System.out.println(list);
		ResetPasswordToken token1 = new ResetPasswordToken();
		if (list.size() > 0) {
			token1 = list.get(0);
		}
		return token1;
	}

	@Override
	public User getUserByPasswordResetToken(String token) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<ResetPasswordToken> query = currentSession.createQuery("from ResetPasswordToken where token=:token",
				ResetPasswordToken.class);
		query.setParameter("token", token);
		List<ResetPasswordToken> list = query.getResultList();
		System.out.println(list);
		ResetPasswordToken resetpasswordtoken = new ResetPasswordToken();
		if (list.size() > 0) {
			resetpasswordtoken = list.get(0);
		}
		System.out.println(list);
		return resetpasswordtoken.getUser();
	}

	@Override
	public void changeUserPassword(String email, String password) {

	}
}
