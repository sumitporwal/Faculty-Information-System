package DAO;

import java.util.List;

import Model.LeaveApplication;

public interface LeaveApplication_DAO {

	public LeaveApplication findByLeaveApplication(String leave_application);

	public void applyForLeave(LeaveApplication leave_application);

	public void deleteLeaveApplication(LeaveApplication leave_application);

	public List<LeaveApplication> getLeaveApplicationList();

	public void updateLeaveApplication(LeaveApplication leave_application);

	public LeaveApplication findById(long leaveapplication_id);

	public List<LeaveApplication> getLeaveApplicationListByDepartment(String department);
	
	public List<LeaveApplication> getLeaveApplicationListByUser(long user_id);
}
