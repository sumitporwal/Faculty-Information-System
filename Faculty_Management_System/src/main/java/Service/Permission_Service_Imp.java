package Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.Permission_DAO;
import Model.Permission;
import Model.Role;

@Service
@Transactional
public class Permission_Service_Imp implements Permission_Service {

	@Autowired
	Permission_DAO permission_dao;
	
	@Override
	public Permission findByPermission(String permission) {
		return permission_dao.findByPermission(permission);
	}

	@Override
	public void addPermission(Permission permission) {
		Permission permission1=permission_dao.findByPermission(permission.getPermission_name());
		if(permission1.getPermission_name()==null) {
			permission_dao.addPermission(permission);
		}
	}

	@Override
	public void deletePermission(Permission permission) {
		permission_dao.deletePermission(permission);
	}

	@Override
	public List<Permission> getPermissionList() {
		return permission_dao.getPermissionList();
	}

	@Override
	public Permission findPermissionByRole(String role) {
		return permission_dao.findPermissionByRole(role);
	}

}
