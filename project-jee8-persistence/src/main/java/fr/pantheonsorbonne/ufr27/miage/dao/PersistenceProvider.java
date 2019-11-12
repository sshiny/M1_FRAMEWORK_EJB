package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Dependent
public class PersistenceProvider {

	@PostConstruct
	void init() {
		System.out.println("pers prod");
	}

	@PersistenceContext(unitName = "default")
	private EntityManagerFactory factory;

	
	@Produces
	public EntityManager getEM() {
		return factory.createEntityManager();
	}

}
