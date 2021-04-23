package Service;

import java.time.LocalDateTime;

import Model.ResetPasswordToken;
import Model.User;

public interface ResetPasswordTokenService {
	public void createPasswordResetTokenForUser(User user, String token,LocalDateTime expiryDate);
	public String validatePasswordResetToken(String token);
	public boolean isTokenFound(ResetPasswordToken passToken);
	public boolean isTokenExpired(ResetPasswordToken passToken);
	public User getUserByPasswordResetToken(String token);
}
