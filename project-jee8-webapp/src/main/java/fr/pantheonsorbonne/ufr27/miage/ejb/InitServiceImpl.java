package fr.pantheonsorbonne.ufr27.miage.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.AirportDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlyJPA;

public class InitServiceImpl implements InitService {
	
	@Inject
	EntityManager em;
	
	@Inject
	FlightDAO flightDao;
	
	@Inject
	AirportDAO AirportDao;

	@Override
	public void init() {
		em.getTransaction().begin();
		FlyJPA f = new FlyJPA();
		em.persist(f);
		f = new FlyJPA();
		em.persist(f);
		f = new FlyJPA();
		em.persist(f);
		em.getTransaction().commit();
	}

}
