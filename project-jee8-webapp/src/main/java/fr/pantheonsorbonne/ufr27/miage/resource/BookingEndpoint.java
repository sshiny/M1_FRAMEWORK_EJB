package fr.pantheonsorbonne.ufr27.miage.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import fr.pantheonsorbonne.ufr27.miage.ejb.BookingService;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.NMRequest;

@Path("bookings")
public class BookingEndpoint {
	
	@Inject
	BookingService service;

	@Consumes(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Path("{id}/add-passenger")
	public Booking addPassenger(NMRequest request) {
		try {
			return service.getAll(request);
		} catch (Exception e) {
			if (e instanceof NoBookingFindException) {
				throw new WebApplicationException("booking no found", 404);
			}
			throw new WebApplicationException(500);
		}
	}
}
