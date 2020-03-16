package fr.pantheonsorbonne.ufr27.miage.ejb;

import fr.pantheonsorbonne.ufr27.miage.jpa.PassengerJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Passenger;

public interface PassengerService {
	public PassengerJPA create(Passenger passenger);
}
