package fr.pantheonsorbonne.ufr27.miage.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

import fr.pantheonsorbonne.ufr27.miage.ejb.BookingService;
import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchBookingException;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.NMRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.SSRequest;

@Path("bookings")
public class BookingEndpoint {
	
	@Inject
	BookingService service;

	@Consumes(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Path("{id}/add-seats")
	public Booking addSeats(@PathParam("id") String id, SSRequest request) {
		try {
			return service.addSeats(id, request);
		} catch (Exception e) {
			if (e instanceof NoSuchBookingException) {
				throw new WebApplicationException("booking no found", 404);
			}
			throw new WebApplicationException(500);
		}
	}
	
	@Consumes(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Path("{id}/add-passenger")
	public Booking addPassenger(@PathParam("id") String id, NMRequest request) {
		try {
			return service.addPassenger(id, request);
		} catch (Exception e) {
			if (e instanceof NoSuchBookingException) {
				throw new WebApplicationException("booking no found", 404);
			}
			throw new WebApplicationException(500);
		}
	}
	

	@Consumes(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@DELETE
	@Path("{id}/cancel")
	public Response cancel(@PathParam("id") String id) {
		try {
			return service.cancel(id);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
