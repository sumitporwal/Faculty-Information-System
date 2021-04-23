package Model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="bus")
public class Bus {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long bus_id;
	
	   @Column(name="bus_no")
	   @NotNull
	   private long bus_no;
	   
	   @Column(name="driver_name")
	   @NotNull
	   private String driver_name;
	   
	   @Column(name="phno")
	   @NotNull
	   private String phno;
	   
	   @Column(name="from_value")
	   @NotNull
	   private String from;
	   
	   @Column(name="to_value")
	   @NotNull
	   private String to;

	   @Column(name="start_time")
	   @NotNull
	   private String start_time;
	   
	   @Column(name="end_time")
	   @NotNull
	   private String end_time;
	   	   
	   @Column(name="status")
	   @NotNull
	   private String Status;
	   
	   @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	    @JoinColumn( nullable = false,name = "user_id")
	    private User user;
	   
	/*   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	   @JoinTable(
	           name = "bus_stops",
	           joinColumns = @JoinColumn(name = "bus_id"),
	           inverseJoinColumns = @JoinColumn(name = "stop_id")
	           )
	   @ManyToOne
	    private BusMidStops bus_mid_stops;*/
	   @JsonIgnore
	   @OneToMany(mappedBy = "bus", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	    private List<BusMidStops> bus_mid_stops;

	   
	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Bus(long bus_id, long bus_no, String driver_name, String phno, String from, String to, String start_time,
			String end_time, String status, User user) {
		super();
		this.bus_id = bus_id;
		this.bus_no = bus_no;
		this.driver_name = driver_name;
		this.phno = phno;
		this.from = from;
		this.to = to;
		this.start_time = start_time;
		this.end_time = end_time;
		Status = status;
		this.user = user;
	}


	public long getBus_id() {
		return bus_id;
	}

	public void setBus_id(long bus_id) {
		this.bus_id = bus_id;
	}

	public long getBus_no() {
		return bus_no;
	}


	public void setBus_no(long bus_no) {
		this.bus_no = bus_no;
	}


	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<BusMidStops> getBus_mid_stops() {
		return bus_mid_stops;
	}

	public void setBus_mid_stops(List<BusMidStops> bus_mid_stops) {
		this.bus_mid_stops = bus_mid_stops;
	}



}
