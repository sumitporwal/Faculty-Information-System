package Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import DAO.ResetPasswordToken_DAO;
import Model.ResetPasswordToken;
import Model.User;

@Service
@Transactional
public class ResetPasswordTokenService_Impl implements ResetPasswordTokenService {
	
	@Autowired
	private ResetPasswordToken_DAO resetpasswordtoken_dao;
	
	@Override
	public void createPasswordResetTokenForUser(User user, String token,LocalDateTime creationDate) {
	        ResetPasswordToken myToken = new ResetPasswordToken(token, user,creationDate);
	        System.out.println(myToken.getExpiration());
	        resetpasswordtoken_dao.save(user,myToken);
	}

	@Override
	public String validatePasswordResetToken(String token) {
		final ResetPasswordToken passToken = resetpasswordtoken_dao.findByToken(token);
	    return !isTokenFound(passToken) ? "invalidToken"
	            : isTokenExpired(passToken) ? "expired"
	            : null;
	}

	@Override
	public boolean isTokenFound(ResetPasswordToken passToken) {
		return passToken != null;
	}

	@Override
	public boolean isTokenExpired(ResetPasswordToken passToken) {
		LocalDateTime now=LocalDateTime.now();
		Duration diff=Duration.between(passToken.getCreationDate(), now);
		System.out.println(diff.toMinutes());
	    return diff.toMinutes() >= passToken.getExpiration();
	}

	@Override
	public User getUserByPasswordResetToken(String token) {
		return resetpasswordtoken_dao.getUserByPasswordResetToken(token);
	}

}
