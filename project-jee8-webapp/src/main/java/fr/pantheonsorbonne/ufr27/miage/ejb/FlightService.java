package fr.pantheonsorbonne.ufr27.miage.ejb;

import java.util.Collection;

import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;

public interface FlightService {
	public Collection<Flight> getAll(AvailabilityNeutralRequest request) throws Exception;
}
