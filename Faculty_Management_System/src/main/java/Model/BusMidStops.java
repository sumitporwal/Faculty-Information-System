package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="bus_mid_stops")
public class BusMidStops {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long stop_id;
	   
	   @Column(name="stop_name")
	   @NotNull
	   private String stop_name;
	   
	   @Column(name="stop_time")
	   @NotNull
	   private String stop_time;
	   
	   @ManyToOne(targetEntity = Bus.class, fetch = FetchType.EAGER)
	    @JoinColumn( nullable = false,name = "bus_id")
	    private Bus bus;

	public BusMidStops() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusMidStops(long stop_id, String stop_name, String stop_time, Bus bus) {
		super();
		this.stop_id = stop_id;
		this.stop_name = stop_name;
		this.stop_time = stop_time;
		this.bus = bus;
	}



	public long getStop_id() {
		return stop_id;
	}

	public void setStop_id(long stop_id) {
		this.stop_id = stop_id;
	}

	public String getStop_name() {
		return stop_name;
	}

	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	public String getStop_time() {
		return stop_time;
	}

	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	 
	
	   
}
