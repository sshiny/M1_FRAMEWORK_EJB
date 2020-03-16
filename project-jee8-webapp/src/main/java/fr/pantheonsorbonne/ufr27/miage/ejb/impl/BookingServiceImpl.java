package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.BookingDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.BookingService;
import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchBookingException;
import fr.pantheonsorbonne.ufr27.miage.exception.NotEnoughSeatsBookedException;
import fr.pantheonsorbonne.ufr27.miage.jpa.BookingJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.PassengerJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.FlightPrice;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Passenger;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.SSRequest;

public class BookingServiceImpl implements BookingService {
	
	@Inject
	BookingDAO bookingDao;
	
	@Inject
	FlightDAO flightDao;
	
	@Inject
	PassengerDAO passengerDao;
	
	@Inject
	EntityManager em;
	
	@Inject
	ObjectFactory factory;
	
	@Override
	public Booking book(int flightId, SSRequest request) throws Exception {
        int seats = request.getNumberOfSeats().intValue();
        String klass = request.getKlass();
        FlightJPA flight = flightDao.updateSeats(flightId, seats, klass);
        BookingJPA booking = bookingDao.create(flight, seats, klass);
		Booking res = factory.createBooking();
		res.setId(booking.getId());
		return res;
	}

	@Override
	public String cancel(String id) throws Exception {
		BookingJPA booking = bookingDao.delete(id);
		FlightJPA flight = booking.getFlight();
		Stack<String> stack = new Stack<>();
		Iterator<Map.Entry<String, Integer>> it = booking.getSeats().entrySet().iterator(); 
		while (it.hasNext()) {
			Map.Entry<String, Integer> elem = it.next();
			stack.push(elem.getKey());
		}
		while (!stack.empty()) {
			String klass = stack.pop();
			switch (klass) {
				case "A":
					flight.setNbPlacesA(flight.getNbPlacesA() + 1);
					flight.setPrice(flight.getPrice() - 10);
					break;
				case "B":
					flight.setNbPlacesB(flight.getNbPlacesB() + 1);
					flight.setPrice(flight.getPrice() - 10);
					break;
				case "C":
					flight.setNbPlacesC(flight.getNbPlacesC() + 1);
					flight.setPrice(flight.getPrice() - 10);
					break;
				default:
					break;
			}
		}
		return booking.getId();
	}

	@Override
	public Booking addSeats(String id, SSRequest request) throws Exception {
		BookingJPA booking = em.find(BookingJPA.class, id);
		if (booking == null) {
			throw new NoSuchBookingException();
		}
        int seats = request.getNumberOfSeats().intValue();
        String klass = request.getKlass();
        flightDao.updateSeats(booking.getFlight().getId(), seats, klass);
        bookingDao.updateSeats(booking.getId(), seats, klass);
		Booking res = factory.createBooking();
		res.setId(booking.getId());
		return res;
	}

	@Override
	public Booking addPassenger(String id, Passenger passenger) throws Exception {
		BookingJPA booking = em.find(BookingJPA.class, id);
		if (booking == null) {
			throw new NoSuchBookingException();
		}
		if (booking.getPassengers().size() >= booking.getSeats().size()) {
			throw new NotEnoughSeatsBookedException();
		}
        PassengerJPA p = passengerDao.create(passenger);
        bookingDao.updatePassengers(booking.getId(), p);
		Booking res = factory.createBooking();
		res.setId(booking.getId());
		return res;
	}

	@Override
	public List<FlightPrice> payment(String id) throws Exception {
		BookingJPA booking = em.find(BookingJPA.class, id);
		if (booking == null) {
			throw new NoSuchBookingException();
		}
		Stack<String> stack = new Stack<>();
		Iterator<Map.Entry<String, Integer>> it = booking.getSeats().entrySet().iterator(); 
		while (it.hasNext()) {
			Map.Entry<String, Integer> elem = it.next();
			stack.push(elem.getKey());
		}
		List<FlightPrice> res = new ArrayList<>();
		for (PassengerJPA passenger : booking.getPassengers()) {
			FlightPrice fp = factory.createFlightPrice();
			fp.setTitle(passenger.getLname() + "/" + passenger.getFname());
			double fare = booking.getFlight().getPrice();
			String klass = stack.pop();
			switch (klass) {
				case "B":
					fare += 0.1 * fare;
					break;
				case "C":
					fare += 0.2 * fare;
					break;
				default:
					break;
			}
			double fee = 20;
			fp.setFare(fare);
			if (passenger.getGender().equals("MR")) {
				fee = 25;
			}
			fp.setFee(fee);
			fp.setTotal(fare + fee);
			res.add(fp);
		}
		FlightPrice fp = factory.createFlightPrice();
		fp.setTitle("Total");
		double fare = 0;
		double fee = 0;
		for (FlightPrice price : res) {
			fare += price.getFare();
			fee += price.getFee();
		}
		fp.setFare(fare);
		fp.setFee(fee);
		fp.setTotal(fare + fee);
		res.add(fp);
		return res;
	}
}
