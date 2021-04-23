package Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import Model.User;

public interface User_Service extends UserDetailsService {
  User findUserByEmail(String email);
  List<User> getUsersByRole(String role);
  void saveUser(User user,String role);
  void changeUserPassword(User user,String password,String token);
  User findUserById(long user_id);
  void updateUser(User user);
  void deleteUser(User user);
}
