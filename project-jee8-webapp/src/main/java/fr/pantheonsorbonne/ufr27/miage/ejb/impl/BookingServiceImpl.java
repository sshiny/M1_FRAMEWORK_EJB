package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import fr.pantheonsorbonne.ufr27.miage.dao.BookingDAO;
import fr.pantheonsorbonne.ufr27.miage.jpa.ReservationJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;

public class BookingServiceImpl {
	@Inject
	BookingDAO dao;
	
	@Inject
	ObjectFactory factory;
	
	@Override
	public Collection<Booking> getAll(AvailabilityNeutralRequest request) throws Exception {
		List<ReservationJPA> reservation = dao.findAll(
				reservation.get(),
				reservation.getOrigin(),
				reservation.getDestination(),
				reservation.getDepartureTime()
		);
		Collection<Booking> res = new ArrayList<>();
		int i = 0;
		for (ReservationJPA f : res) {
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
