package fr.pantheonsorbonne.ufr27.miage.resource;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/init")
public class InitEndpoint {
	
	@Inject
	EntityManager em;
	
	@POST
	public void init() {
	}
}
