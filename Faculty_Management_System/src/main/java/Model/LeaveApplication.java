package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="leave_application")
public class LeaveApplication {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long leaveapplication_id;
	
	   @Column(name="leave_reason")
	   @NotNull
	   private String leave_reason;
	   
	   @Column(name="leave_from")
	   @NotNull
	   private String leave_from;
	   
	   @Column(name="leave_to")
	   @NotNull
	   private String leave_to;
	   
	   @Column(name="applied_on")
	   @NotNull
	   private String applied_on;
	   
	   @Column(name="status")
	   @NotNull
	   private String status;
	   
	   @Column(name="leave_approver")
	   @NotNull
	   private String leave_approver;
	   
	   @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	    @JoinColumn( nullable = false,name = "user_id")
	    private User user;

	public LeaveApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveApplication(long leaveapplication_id, String leave_reason, String leave_from, String leave_to,
			String applied_on, String status, String leave_approver, User user) {
		super();
		this.leaveapplication_id = leaveapplication_id;
		this.leave_reason = leave_reason;
		this.leave_from = leave_from;
		this.leave_to = leave_to;
		this.applied_on = applied_on;
		this.status = status;
		this.leave_approver = leave_approver;
		this.user = user;
	}

	public long getLeaveapplication_id() {
		return leaveapplication_id;
	}

	public void setLeaveapplication_id(long leaveapplication_id) {
		this.leaveapplication_id = leaveapplication_id;
	}

	public String getLeave_reason() {
		return leave_reason;
	}

	public void setLeave_reason(String leave_reason) {
		this.leave_reason = leave_reason;
	}

	public String getLeave_from() {
		return leave_from;
	}

	public void setLeave_from(String leave_from) {
		this.leave_from = leave_from;
	}

	public String getLeave_to() {
		return leave_to;
	}

	public void setLeave_to(String leave_to) {
		this.leave_to = leave_to;
	}

	public String getApplied_on() {
		return applied_on;
	}

	public void setApplied_on(String applied_on) {
		this.applied_on = applied_on;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeave_approver() {
		return leave_approver;
	}

	public void setLeave_approver(String leave_approver) {
		this.leave_approver = leave_approver;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	   
	   
}
