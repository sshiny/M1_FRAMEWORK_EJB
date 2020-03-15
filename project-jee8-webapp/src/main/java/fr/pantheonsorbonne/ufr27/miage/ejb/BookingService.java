package fr.pantheonsorbonne.ufr27.miage.ejb;

import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.SSRequest;

public interface BookingService {
	public Booking book(int flightId, SSRequest request) throws Exception;
}
