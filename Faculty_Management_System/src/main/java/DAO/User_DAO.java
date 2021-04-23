package DAO;

import java.util.List;

import Model.User;

public interface User_DAO {
	User findByEmail(String email);

	void saveUser(User user);

	void updateUser(User user);

	List<User> getUsersByRole(String role);

	User findById(long user_id);

	void deleteUser(User user);
}
