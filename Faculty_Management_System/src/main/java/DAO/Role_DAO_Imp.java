package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.Role;

@Repository
public class Role_DAO_Imp implements Role_DAO {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Role findByRole(String role) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Role> query = currentSession.createQuery("from Role where role_name=:role", Role.class);
		query.setParameter("role", role);
		List<Role> list = query.getResultList();
		Role role1 = new Role();
		if (list.size() > 0) {
			role1 = list.get(0);
		}
		return role1;
	}

	@Override
	public void addRole(Role role) {
		try {
			sessionfactory.getCurrentSession().save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteRole(Role role) {
		try {
			sessionfactory.getCurrentSession().delete(role);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Role> getRoleList() {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Role> query = currentSession.createQuery("from Role", Role.class);
		List<Role> list = query.getResultList();
		return list;
	}

}
