package fr.pantheonsorbonne.ufr27.miage.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.pantheonsorbonne.ufr27.miage.ejb.GymService;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.FreeTrialPlan;;

@Path("membership/")
public class MemberShipEndpoint {

	@EJB
	GymService service;

	@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@POST
	public Response createUser(FreeTrialPlan plan) throws URISyntaxException {
		int customerId = service.createMembership(plan.getUser().getLname(), plan.getUser().getFname());

		return Response.created(new URI("/user/" + customerId)).build();

	}

}
