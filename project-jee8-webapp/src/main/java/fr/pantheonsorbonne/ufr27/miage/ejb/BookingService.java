package fr.pantheonsorbonne.ufr27.miage.ejb;

import java.util.Collection;

import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.SSRequest;

public interface BookingService {
	public Collection<Booking> book(int flightId, SSRequest request) throws Exception;
	public Collection<Booking> getAll(AvailabilityNeutralRequest request) throws Exception;
}
