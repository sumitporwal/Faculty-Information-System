package Service;

import java.util.List;

import Model.LeaveApplication;

public interface LeaveApplication_Service {

	  public LeaveApplication findByLeaveApplication(String leave_application);
	  public void applyForLeave(LeaveApplication leave_application);
	  public void deleteLeaveApplication(LeaveApplication leave_application);
	  public List<LeaveApplication> getLeaveApplicationList();
	  public void updateLeaveApplication(LeaveApplication leave_application);
	  public LeaveApplication findLeaveApplicationById(long leaveapplication_id);
	  public List<LeaveApplication> getLeaveApplicationListByDepartment(String department);
	  public List<LeaveApplication> getLeaveApplicationListByUser(long user_id);
}
