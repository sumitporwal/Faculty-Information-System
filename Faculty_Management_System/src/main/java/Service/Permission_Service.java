package Service;

import java.util.List;

import Model.Permission;

public interface Permission_Service {

	  public Permission findByPermission(String permission);
	  public void addPermission(Permission permission);
	  public void deletePermission(Permission permission);
	  public List<Permission> getPermissionList();
	  public Permission findPermissionByRole(String role);

}
