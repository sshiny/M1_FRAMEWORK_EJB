package fr.pantheonsorbonne.ufr27.miage.resource;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.pantheonsorbonne.ufr27.miage.ejb.FlightService;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;

@Path("flights/")
public class FlightEndpoint {
	
	@Inject
	FlightService service;

	@Consumes(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Response getAll(AvailabilityNeutralRequest request) throws URISyntaxException {
		Collection<Flight> flights = service.getAll(request);
		return Response.ok().build();
	}
}
