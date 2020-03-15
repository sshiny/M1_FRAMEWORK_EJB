package fr.pantheonsorbonne.ufr27.miage.resource;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import fr.pantheonsorbonne.ufr27.miage.ejb.InitService;

@Path("/init")
public class InitEndpoint {
	
	@Inject
	InitService service;
	
	@POST
	public void init() {
		service.init();
	}
}
