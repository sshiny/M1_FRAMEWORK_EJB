package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchUserException;
import fr.pantheonsorbonne.ufr27.miage.jpa.Customer;
import fr.pantheonsorbonne.ufr27.miage.jpa.PassagerJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Address;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Passenger;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.User;

public class PassengerDAO {
	@Inject
	EntityManager em;

	public Passenger getUserFromId(int id) throws NoSuchUserException {

		PassagerJPA passager = em.find(PassagerJPA.class, id);
		if(passager==null) {
			throw new NoSuchUserException();
		}
		Passenger pass = new ObjectFactory().createPassenger();
		pass.setFname(passager.getNom());
		pass.setLname(passager.getPrenom());
		pass.setSexe(passager.getSexe());
		return pass;
	}
}
