package fr.pantheonsorbonne.ufr27.miage.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import fr.pantheonsorbonne.ufr27.miage.ejb.PassengerService;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAirportForSuchCityException;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Passenger;

@Path("passenger")
public class PassengerEndpoint {
	
	@Inject
	PassengerService service;
	
	@Consumes(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@POST
	@Path("create")
	public Collection<Flight> create(Passenger passenger) {
		try {
			return service.create(passenger);
		} catch (Exception e) {
			if (e instanceof NoAirportForSuchCityException) {
				throw new WebApplicationException("airport no found", 404);
			}
			throw new WebApplicationException(500);
		}
	}
}
