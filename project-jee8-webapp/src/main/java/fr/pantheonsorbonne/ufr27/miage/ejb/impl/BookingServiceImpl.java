package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.BookingDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.BookingService;
import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchBookingException;
import fr.pantheonsorbonne.ufr27.miage.jpa.BookingJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.PassengerJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.NMRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;
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
        flightDao.updateSeats(flightId, seats, klass);
        BookingJPA booking = bookingDao.create(seats, klass);
		Booking res = factory.createBooking();
		res.setId(booking.getId());
		return res;
	}

	@Override
	public String cancel(String id) throws Exception {
		BookingJPA booking = bookingDao.delete(id);
		FlightJPA flight = booking.getFlight();
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
	public Booking addPassenger(String id, NMRequest request) throws Exception {
		BookingJPA booking = em.find(BookingJPA.class, id);
		if (booking == null) {
			throw new NoSuchBookingException();
		}
        PassengerJPA passenger = passengerDao.create(request.getPassenger());
        bookingDao.updatePassengers(booking.getId(), passenger);
		Booking res = factory.createBooking();
		res.setId(booking.getId());
		return res;
	}
}
