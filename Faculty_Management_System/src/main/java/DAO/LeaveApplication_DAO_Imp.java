package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.Bus;
import Model.LeaveApplication;
import Model.User;

@Repository
public class LeaveApplication_DAO_Imp implements LeaveApplication_DAO {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public LeaveApplication findByLeaveApplication(String leave_application) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<LeaveApplication> query = currentSession.createQuery("from LeaveApplication where subject=:subject",
				LeaveApplication.class);
		query.setParameter("subject", leave_application);
		List<LeaveApplication> list = query.getResultList();
		LeaveApplication leave_application1 = new LeaveApplication();
		if (list.size() > 0) {
			leave_application1 = list.get(0);
		}
		return leave_application1;
	}

	@Override
	public void applyForLeave(LeaveApplication leave_application) {
		sessionfactory.getCurrentSession().save(leave_application);
	}

	@Override
	public void deleteLeaveApplication(LeaveApplication leave_application) {
		try {
			sessionfactory.getCurrentSession().delete(leave_application);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LeaveApplication> getLeaveApplicationList() {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<LeaveApplication> query = currentSession.createQuery("from LeaveApplication", LeaveApplication.class);
		List<LeaveApplication> list = query.getResultList();
		return list;
	}

	@Override
	public void updateLeaveApplication(LeaveApplication leave_application) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			LeaveApplication leave_application1 = findById(leave_application.getLeaveapplication_id());
			leave_application1.setLeave_reason(leave_application1.getLeave_reason());
			;
			leave_application1.setLeave_from(leave_application1.getLeave_from());
			leave_application1.setLeave_to(leave_application1.getLeave_to());
			leave_application1.setStatus(leave_application.getStatus());
			leave_application1.setLeave_approver(leave_application.getLeave_approver());
			leave_application1.setUser(leave_application1.getUser());
			currentSession.update(leave_application1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LeaveApplication findById(long leaveapplication_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<LeaveApplication> query = currentSession.createQuery(
				"from LeaveApplication where leaveapplication_id=:leaveapplication_id", LeaveApplication.class);
		query.setParameter("leaveapplication_id", leaveapplication_id);
		List<LeaveApplication> list = query.getResultList();
		System.out.println(list);
		LeaveApplication leave_application1 = new LeaveApplication();
		if (list.size() > 0) {
			leave_application1 = list.get(0);
		}
		return leave_application1;
	}

	@SuppressWarnings("null")
	@Override
	public List<LeaveApplication> getLeaveApplicationListByDepartment(String department) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User where department=:department", User.class);
		query.setParameter("department", department);
		List<User> user = query.getResultList();
		List<LeaveApplication> list = null;
		for (int i = 0; i < user.size(); i++) {
			Query<LeaveApplication> query1 = currentSession.createQuery("from LeaveApplication where user_id=:user_id",
					LeaveApplication.class);
			query1.setParameter("user_id", user.get(i).getUser_id());
			if (query1.getResultList().size() > 0) {
				list.addAll(query1.getResultList());
			}
		}
		return list;
	}

	@Override
	public List<LeaveApplication> getLeaveApplicationListByUser(long user_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<LeaveApplication> query = currentSession.createQuery(
				"from LeaveApplication where user_id=:user_id", LeaveApplication.class);
		query.setParameter("user_id", user_id);
		List<LeaveApplication> list = query.getResultList();
		System.out.println(list);
		if (list.size() > 0) {
			return list;
		}
		return null;

	}
}
