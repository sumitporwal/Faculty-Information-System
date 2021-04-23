package Service;

import java.util.List;

import Model.Role;

public interface Role_Service {
	  public Role findByRole(String role);
	  public void addRole(Role role);
	  public void deleteRole(Role role);
	  public List<Role> getRoleList();
}
