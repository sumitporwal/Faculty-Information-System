package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.Permission;
import Model.Role;

@Repository
public class Permission_DAO_imp implements Permission_DAO {
	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Permission findByPermission(String permission) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Permission> query = currentSession.createQuery("from Permission where permission_name=:permission",
				Permission.class);
		query.setParameter("permission", permission);
		List<Permission> list = query.getResultList();
		Permission permission1 = new Permission();
		if (list.size() > 0) {
			permission1 = list.get(0);
		}
		return permission1;
	}

	@Override
	public void addPermission(Permission permission) {
		try {
			sessionfactory.getCurrentSession().save(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deletePermission(Permission permission) {
		try {
			sessionfactory.getCurrentSession().delete(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Permission> getPermissionList() {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Permission> query = currentSession.createQuery("from Permission", Permission.class);
		List<Permission> list = query.getResultList();
		return list;
	}

	@Override
	public Permission findPermissionByRole(String role) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Role> query = currentSession.createQuery("from Role  where role_name=:role_name", Role.class);
		query.setParameter("role_name", role);
		List<Role> list = query.getResultList();

		System.out.println(list);
		if (list.size() > 0) {
			return list.get(0).getPermissions();
		}
		return null;

	}

}
