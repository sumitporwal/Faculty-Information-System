package Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.LeaveApplication_DAO;
import DAO.User_DAO;
import Model.LeaveApplication;
import Model.User;

@Service
@Transactional
public class LeaveApplication_Service_Imp implements LeaveApplication_Service {
	
	@Autowired
	LeaveApplication_DAO leaveapplication_dao;
	
	@Autowired
	User_DAO user_dao;

	@Override
	public LeaveApplication findByLeaveApplication(String leave_application) {
		return leaveapplication_dao.findByLeaveApplication(leave_application);
	}

	@Override
	public void applyForLeave(LeaveApplication leave_application) {
		Date date=Calendar.getInstance().getTime();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String generationDate=dateFormat.format(date);
		leave_application.setApplied_on(generationDate);
		leave_application.setStatus("Pending");
		leaveapplication_dao.applyForLeave(leave_application);
	}

	@Override
	public void deleteLeaveApplication(LeaveApplication leave_application) {
		leaveapplication_dao.deleteLeaveApplication(leave_application);
	}

	@Override
	public List<LeaveApplication> getLeaveApplicationList() {
		return leaveapplication_dao.getLeaveApplicationList();
	}

	@Override
	public void updateLeaveApplication(LeaveApplication leave_application) {
		leaveapplication_dao.updateLeaveApplication(leave_application);
	}

	@Override
	public LeaveApplication findLeaveApplicationById(long leaveapplication_id) {
		return leaveapplication_dao.findById(leaveapplication_id);
	}
	
	@Override
	public List<LeaveApplication> getLeaveApplicationListByDepartment(String department) {
			return leaveapplication_dao.getLeaveApplicationListByDepartment(department);
	}

	@Override
	public List<LeaveApplication> getLeaveApplicationListByUser(long user_id) {
		return leaveapplication_dao.getLeaveApplicationListByUser(user_id);
	}

}
