package fr.pantheonsorbonne.ufr27.miage.ejb;

import java.util.List;

import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;

public interface FlightService {
	public List<Flight> getAll(AvailabilityNeutralRequest request) throws Exception;
}
