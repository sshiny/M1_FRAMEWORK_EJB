package fr.pantheonsorbonne.ufr27.miage.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import fr.pantheonsorbonne.ufr27.miage.ejb.BookingService;
import fr.pantheonsorbonne.ufr27.miage.ejb.FlightService;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAirportForSuchCityException;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Booking;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.SSRequest;

@Path("flights")
public class FlightEndpoint {
	
	@Inject
	FlightService flightService;
	
	@Inject
	BookingService bookingService;
	
	@Inject
	ObjectFactory factory;

	@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Path("{date}/{origin}/{destination}/{departureTime}")
	public Collection<Flight> getAll(
		@PathParam("date") String date,
		@PathParam("origin") String origin,
		@PathParam("destination") String destination,
		@PathParam("departureTime") String departureTime)
	{
		try {
			AvailabilityNeutralRequest req = factory.createAvailabilityNeutralRequest();
			req.setDate(date);
			req.setOrigin(origin);
			req.setDestination(destination);
			req.setDepartureTime(departureTime);
			return flightService.getAll(req);
		} catch (Exception e) {
			if (e instanceof NoAirportForSuchCityException) {
				throw new WebApplicationException("airport no found", 404);
			}
			throw new WebApplicationException(500);
		}
	}

	@Consumes(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@POST
	@Path("{id}/book")
	public Booking book(@PathParam("id") int id, SSRequest req) {
		try {
			return bookingService.book(id, req);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
