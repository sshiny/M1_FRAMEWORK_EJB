package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchUserException;
import fr.pantheonsorbonne.ufr27.miage.jpa.PassengerJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Passenger;

public class PassengerDAO {
	
	@Inject
	EntityManager em;

	public PassengerJPA create(Passenger passenger) {
		em.getTransaction().begin();
		PassengerJPA toPersist = new PassengerJPA();
		toPersist.setLname(passenger.getLname());
		toPersist.setFname(passenger.getFname());
		toPersist.setGender(passenger.getGender());
		em.persist(toPersist);
		em.getTransaction().commit();
		return toPersist;
	}

	public Passenger getUserFromId(int id) throws NoSuchUserException {

		PassengerJPA passager = em.find(PassengerJPA.class, id);
		if(passager==null) {
			throw new NoSuchUserException();
		}
		Passenger pass = new ObjectFactory().createPassenger();
		pass.setFname(passager.getFname());
		pass.setLname(passager.getLname());
		pass.setGender(passager.getGender());
		return pass;
	}
}
