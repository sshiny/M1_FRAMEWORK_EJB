package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.FlightService;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;

public class FlightServiceImpl implements FlightService {
	
	@Inject
	FlightDAO dao;
	
	@Inject
	ObjectFactory factory;
	
	@Override
<<<<<<< HEAD
	public Collection<Flight> getAll(AvailabilityNeutralRequest request) {
		return null ;//dao.findAll(request.getDate(), request.getOrigin(), request.getDestination(), request.getDepartureTime());
=======
	public Collection<Flight> getAll(AvailabilityNeutralRequest request) throws Exception {
		List<FlightJPA> flights = dao.findAll(
			request.getDate(),
			request.getOrigin(),
			request.getDestination(),
			request.getDepartureTime()
		);
		Collection<Flight> res = new ArrayList<>();
		int i = 0;
		for (FlightJPA f : flights) {
			Flight flight = factory.createFlight();
			flight.setId(BigInteger.valueOf(++i));
		    flight.setIdCompany(f.getCompany());
		    flight.setAvailability(f.getGetAvailabilities());
		    flight.setOrigin(f.getOrigin());
		    flight.setDestination(f.getDestination());
		    flight.setDepartureTime(f.getDateDep());
		    flight.setArrivalTime(f.getDuration());
		    flight.setDuration(f.getDuration());
			res.add(flight);
		}
		return res;
>>>>>>> 9ae81133ef9ee656f210f11cb5064890b8f2086f
	}
}	
