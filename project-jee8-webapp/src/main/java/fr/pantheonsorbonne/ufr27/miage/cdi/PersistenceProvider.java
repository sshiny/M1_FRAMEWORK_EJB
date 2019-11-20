package fr.pantheonsorbonne.ufr27.miage.cdi;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean
public class PersistenceProvider {

	@PostConstruct
	void init() {
		System.out.println("pers prod");
	}

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Produces
	public EntityManager getEM() {
		return em;
	}

}
