package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.AdditionalUserDetails;
import Model.Role;
import Model.User;

@Repository
public class User_DAO_Imp implements User_DAO {

	@Autowired
	private SessionFactory sessionfactory;
	
	@Autowired
	AdditionalUserDetails_DAO additionaluserdetails_dao;

	@Override
	public User findByEmail(String email) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User where email=:email", User.class);
		query.setParameter("email", email);
		List<User> list = query.getResultList();
		System.out.println(list);
		User user1 = new User();
		if (list.size() > 0) {
			user1 = list.get(0);
		}
		return user1;
	}

	@Override
	public void saveUser(User user) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			currentSession.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateUser(User user) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			User u = findByEmail(user.getEmail());
			System.out.println(user.isEnabled());
			u.setName(user.getName());
			u.setEmail(user.getEmail());
			u.setPhno(user.getPhno());
			u.setDob(user.getDob());
			u.setRoles(u.getRoles());
			u.setEnabled(user.isEnabled());
			u.setDepartment(user.getDepartment());
			u.setStream(user.getStream());
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			currentSession.update(u);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<User> getUsersByRole(String role) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Role> query = currentSession.createQuery("from Role  where role_name=:role_name", Role.class);
		query.setParameter("role_name", role);
		List<Role> list = query.getResultList();

		System.out.println(list);
		if (list.size() > 0) {
			Session currentSession1 = sessionfactory.getCurrentSession();
			Query<User> query1 = currentSession1.createQuery("from User where role_id=:role_id", User.class);
			query1.setParameter("role_id", list.get(0));
			List<User> list1 = query1.getResultList();
			return list1;
		}
		return null;
	}

	@Override
	public User findById(long user_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User where user_id=:user_id", User.class);
		query.setParameter("user_id", user_id);
		List<User> list = query.getResultList();
		System.out.println(list);
		User user1 = new User();
		if (list.size() > 0) {
			user1 = list.get(0);
		}
		return user1;
	}

	@Override
	public void deleteUser(User user) {
		try {
			sessionfactory.getCurrentSession().delete(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
