package Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.Role_DAO;
import Model.Role;

@Service
@Transactional
public class Role_Service_Impl implements Role_Service {
	
	@Autowired
	 private Role_DAO role_dao;

	@Override
	public Role findByRole(String role) {
		return role_dao.findByRole(role);
	}

	@Override
	public void addRole(Role role) {
		Role role1=role_dao.findByRole(role.getRole_name());
		if(role1.getRole_name()==null) {
			role_dao.addRole(role);
		}
	}

	@Override
	public void deleteRole(Role role) {
		role_dao.deleteRole(role);
	}

	@Override
	public List<Role> getRoleList() {
		return role_dao.getRoleList();
	}

}
