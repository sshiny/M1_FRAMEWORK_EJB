package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import javax.inject.Inject;

import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.PassengerService;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Passenger;

public class PassengerServiceImpl implements PassengerService {
	
	@Inject
	PassengerDAO dao;

	@Override
	public boolean create(Passenger passenger) {
		return dao.create(passenger);
	}

}
