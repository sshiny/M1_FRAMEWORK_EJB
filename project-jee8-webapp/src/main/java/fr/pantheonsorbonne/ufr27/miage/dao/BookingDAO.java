package fr.pantheonsorbonne.ufr27.miage.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchBookingException;
import fr.pantheonsorbonne.ufr27.miage.jpa.BookingJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.PassengerJPA;

public class BookingDAO {
	
	@Inject
	EntityManager em;

	@Inject
	FlightDAO flightDao;
	
	public BookingJPA create(FlightJPA flight, int seats, String klass) {
		em.getTransaction().begin();
		BookingJPA booking = new BookingJPA();
		booking.setFlight(flight);
		Map<String, Integer> map = new HashMap<>();
		map.put(klass, seats);
		booking.setSeats(map);
		em.persist(booking);
		em.getTransaction().commit();
		return booking;
	}
	
	public BookingJPA updateSeats(String id, int seats, String klass) throws NoSuchBookingException  {
		BookingJPA booking = em.find(BookingJPA.class, id);
		if (booking == null) {
			throw new NoSuchBookingException();
		}
		em.getTransaction().begin();
		Map<String, Integer> map = booking.getSeats();
		if (map.containsKey(klass)) {
			map.put(klass, map.get(klass) + new Integer(seats));
		} else {
			map.put(klass, seats);
		}
		booking.setSeats(map);
		em.merge(booking);
		em.getTransaction().commit();
		return booking;
	}
	
	public BookingJPA updatePassengers(String id, PassengerJPA passenger) throws NoSuchBookingException  {
		BookingJPA booking = em.find(BookingJPA.class, id);
		if (booking == null) {
			throw new NoSuchBookingException();
		}
		em.getTransaction().begin();
		Set<PassengerJPA> set = booking.getPassengers();
		set.add(passenger);
		booking.setPassengers(set);
		em.merge(booking);
		em.getTransaction().commit();
		return booking;
	}
	
	public BookingJPA delete(String id) throws NoSuchBookingException {
		BookingJPA booking = em.find(BookingJPA.class, id);
		if (booking == null) {
			throw new NoSuchBookingException();
		}
		em.getTransaction().begin();
		em.remove(booking);
		em.getTransaction().commit();
		return booking;
	}
}
