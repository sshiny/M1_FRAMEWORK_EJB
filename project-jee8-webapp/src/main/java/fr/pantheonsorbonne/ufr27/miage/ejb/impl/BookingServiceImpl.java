package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.BookingDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.BookingService;
import fr.pantheonsorbonne.ufr27.miage.jpa.BookingJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.SSRequest;

public class BookingServiceImpl implements BookingService {
	
	@Inject
	BookingDAO bookingDao;
	
	@Inject
	FlightDAO flightDao;
	
	@Inject
	EntityManager em;
	
	@Inject
	ObjectFactory factory;
	
	@Override
	public Booking book(int flightId, SSRequest request) throws Exception {
        int seats = request.getNumberOfSeats().intValue();
        String klass = request.getKlass();
        flightDao.updateSeats(flightId, seats, klass);
        BookingJPA booking = bookingDao.create();
		Booking res = factory.createBooking();
		res.setId(booking.getId());
		return res;
	}
}
