package Service;

import java.util.List;

import Model.Bus;
import Model.BusMidStops;


public interface Bus_Service {

	  public Bus findByBus(String bus);
	  public void addBus(Bus bus);
	  public void deleteBus(Bus bus);
	  public List<Bus> getBusList();
	  public void updateBus(Bus bus);
	  public Bus findBusById(long bus_id);
	  public void addBusStops(BusMidStops bus_mid_stops);
	  public void deleteBusStops(BusMidStops bus_mid_stops);
	  public List<BusMidStops> getBusStopsList(long bus_id);
	  public void updateBusStops(BusMidStops bus_mid_stops);
	  public BusMidStops findBusStopsById(long stop_id);
}
