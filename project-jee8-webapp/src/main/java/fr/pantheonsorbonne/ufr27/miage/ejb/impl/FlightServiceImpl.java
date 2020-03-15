package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.FlightService;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;

public class FlightServiceImpl implements FlightService {
	
	@Inject
	FlightDAO dao;
	
	@Inject
	ObjectFactory factory;
	
	@Override
	public Collection<Flight> getAll(AvailabilityNeutralRequest request) throws Exception {
		List<Flight> flights = dao.findAll(
			request.getDate(),
			request.getOrigin(),
			request.getDestination(),
			request.getDepartureTime()
		);
		Collection<Flight> res = new ArrayList<>();
		int i = 0;
		for (Flight f : flights) {
			Flight flight = factory.createFlight();
			flight.setId(BigInteger.valueOf(++i));
		    flight.setIdCompany(f.getIdCompany());
		    flight.setAvailability(f.getAvailability());
		    flight.setOrigin(f.getOrigin());
		    flight.setDestination(f.getDestination());
		    flight.setDepartureTime(f.getDepartureTime());
		    flight.setArrivalTime(f.getArrivalTime());
		    flight.setDuration(f.getDuration());
			res.add(flight);
		}
		return res;
	}
}
