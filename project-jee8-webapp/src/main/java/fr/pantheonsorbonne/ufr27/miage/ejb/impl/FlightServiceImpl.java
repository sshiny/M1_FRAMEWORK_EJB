package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.FlightService;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;

public class FlightServiceImpl implements FlightService {
	
	@Inject
	FlightDAO dao;
	
	@Override
	public Collection<Flight> getAll(AvailabilityNeutralRequest request) {
		return null ;//dao.findAll(request.getDate(), request.getOrigin(), request.getDestination(), request.getDepartureTime());
	}
}
