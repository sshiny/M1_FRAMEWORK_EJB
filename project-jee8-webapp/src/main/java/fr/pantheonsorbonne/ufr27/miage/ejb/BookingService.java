package fr.pantheonsorbonne.ufr27.miage.ejb;

import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.NMRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.SSRequest;

public interface BookingService {
	public Booking book(int flightId, SSRequest request) throws Exception;
	public String cancel(String id) throws Exception;
	public Booking addSeats(String id, SSRequest request) throws Exception;
	public Booking addPassenger(String id, NMRequest request) throws Exception;
}
