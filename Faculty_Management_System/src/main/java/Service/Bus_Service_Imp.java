package Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import DAO.Bus_DAO;
import Model.Bus;
import Model.BusMidStops;

@Service
@Transactional
public class Bus_Service_Imp implements Bus_Service {

	@Autowired
	Bus_DAO bus_dao;
	
	@Override
	public Bus findByBus(String bus) {
		return bus_dao.findByBus(bus);
	}

	@Override
	public void addBus(Bus bus) {		
		bus_dao.addBus(bus);
	}

	@Override
	public void deleteBus(Bus bus) {
		bus_dao.deleteBus(bus);
	}

	@Override
	public List<Bus> getBusList() {
		return bus_dao.getBusList();
	}
	
	@Override
	public void updateBus(Bus bus) {
		bus_dao.updateBus(bus);
	}

	@Override
	public Bus findBusById(long bus_id) {
		return bus_dao.findById(bus_id);
	}
	
	@Override
	public void addBusStops(BusMidStops bus_mid_stops) {		
		bus_dao.addBusStops(bus_mid_stops);
	}

	@Override
	public void deleteBusStops(BusMidStops bus_mid_stops) {
		bus_dao.deleteBusStops(bus_mid_stops);
	}

	@Override
	public List<BusMidStops> getBusStopsList(long bus_id) {
		return bus_dao.getBusStopsList(bus_id);
	}
	
	@Override
	public void updateBusStops(BusMidStops bus_mid_stops) {
		bus_dao.updateBusStops(bus_mid_stops);
	}

	@Override
	public BusMidStops findBusStopsById(long stop_id) {
		return bus_dao.findBusStopsById(stop_id);
	}
}
