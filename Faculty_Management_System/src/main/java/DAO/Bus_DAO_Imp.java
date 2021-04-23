package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.Bus;
import Model.BusMidStops;

@Repository
public class Bus_DAO_Imp implements Bus_DAO {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Bus findByBus(String bus) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Bus> query = currentSession.createQuery("from Bus where subject=:subject", Bus.class);
		query.setParameter("subject", bus);
		List<Bus> list = query.getResultList();
		Bus bus1 = new Bus();
		if (list.size() > 0) {
			bus1 = list.get(0);
		}
		return bus1;
	}

	@Override
	public void addBus(Bus bus) {

		sessionfactory.getCurrentSession().save(bus);

	}

	@Override
	public void deleteBus(Bus bus) {
		try {
			sessionfactory.getCurrentSession().delete(bus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Bus> getBusList() {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Bus> query = currentSession.createQuery("from Bus", Bus.class);
		List<Bus> list = query.getResultList();
		return list;
	}

	@Override
	public void updateBus(Bus bus) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			Bus b = findById(bus.getBus_id());
			b.setBus_no(bus.getBus_no());
			b.setDriver_name(bus.getDriver_name());
			b.setPhno(bus.getPhno());
			b.setFrom(bus.getFrom());
			b.setTo(bus.getTo());
			b.setStart_time(bus.getStart_time());
			b.setEnd_time(bus.getEnd_time());
			b.setStatus(bus.getStatus());
			b.setUser(bus.getUser());
			b.setBus_mid_stops(bus.getBus_mid_stops());
			currentSession.update(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Bus findById(long bus_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Bus> query = currentSession.createQuery("from Bus where bus_id=:bus_id", Bus.class);
		query.setParameter("bus_id", bus_id);
		List<Bus> list = query.getResultList();
		System.out.println(list);
		Bus bus1 = new Bus();
		if (list.size() > 0) {
			bus1 = list.get(0);
		}
		return bus1;
	}

	@Override
	public void addBusStops(BusMidStops bus_mid_stops) {

		sessionfactory.getCurrentSession().save(bus_mid_stops);

	}

	@Override
	public void deleteBusStops(BusMidStops bus_mid_stops) {
		try {
			sessionfactory.getCurrentSession().delete(bus_mid_stops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BusMidStops> getBusStopsList(long bus_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<BusMidStops> query = currentSession.createQuery("from BusMidStops where bus_id=:bus_id", BusMidStops.class);
		query.setParameter("bus_id", bus_id);
		List<BusMidStops> list = query.getResultList();
		return list;
	}

	@Override
	public void updateBusStops(BusMidStops bus_mid_stops) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			BusMidStops bus_stops = findBusStopsById(bus_mid_stops.getStop_id());
			bus_stops.setStop_name(bus_mid_stops.getStop_name());
			bus_stops.setStop_time(bus_mid_stops.getStop_time());
			System.out.println(bus_mid_stops.getStop_name());
			bus_stops.setBus(bus_mid_stops.getBus());
			currentSession.update(bus_stops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BusMidStops findBusStopsById(long stop_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<BusMidStops> query = currentSession.createQuery("from BusMidStops where stop_id=:stop_id", BusMidStops.class);
		query.setParameter("stop_id", stop_id);
		List<BusMidStops> list = query.getResultList();
		System.out.println(list);
		BusMidStops bus_mid_stops1 = new BusMidStops();
		if (list.size() > 0) {
			bus_mid_stops1 = list.get(0);
		}
		return bus_mid_stops1;
	}

}
