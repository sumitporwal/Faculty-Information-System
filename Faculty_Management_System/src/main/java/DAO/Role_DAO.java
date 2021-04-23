package DAO;

import java.util.List;

import Model.Role;

public interface Role_DAO {
	public Role findByRole(String role);

	public void addRole(Role role);

	public void deleteRole(Role role);

	public List<Role> getRoleList();
}
