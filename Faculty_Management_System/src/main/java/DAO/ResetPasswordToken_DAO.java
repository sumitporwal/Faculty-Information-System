package DAO;

import Model.ResetPasswordToken;
import Model.User;

public interface ResetPasswordToken_DAO {
	public void save(User user, ResetPasswordToken resetpasswordtoken);

	public ResetPasswordToken findByToken(String token);

	public User getUserByPasswordResetToken(String token);

	public void changeUserPassword(String email, String password);
}
