package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.BookingDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.BookingService;
import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchFlightException;
import fr.pantheonsorbonne.ufr27.miage.jpa.BookingJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;
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
		Booking res = new Booking();
        FlightJPA flight = em.find(FlightJPA.class, flightId);
        if (flight == null) {
        	throw new NoSuchFlightException();
        }
        em.getTransaction().begin();
        int seats = request.getNumberOfSeats().intValue();
        String klass = request.getKlass();
		em.getTransaction().commit();
		return res;
	}
	
	@Override
	public Collection<Booking> getAll(AvailabilityNeutralRequest request) throws Exception {
		List<BookingJPA> reservation = dao.findAll(
				reservation.get(),
				reservation.getOrigin(),
				reservation.getDestination(),
				reservation.getDepartureTime()
		);
		Collection<Booking> res = new ArrayList<>();
		int i = 0;
		for (BookingJPA f : res) {
			Flight flight = factory.createFlight();
			flight.setId(BigInteger.valueOf(++i));
		    flight.setIdCompany(f.());
		    flight.setAvailability(f.getGetAvailabilities());
		    flight.setOrigin(f.getOrigin());
		    flight.setDestination(f.getDestination());
		    flight.setDepartureTime(f.getDateDep());
		    flight.setArrivalTime(f.getDuration());
		    flight.setDuration(f.getDuration());
			res.add(flight);
		}
		return res;
	}
}
