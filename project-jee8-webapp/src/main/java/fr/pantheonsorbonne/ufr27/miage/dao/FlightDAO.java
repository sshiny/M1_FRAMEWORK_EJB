package fr.pantheonsorbonne.ufr27.miage.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Airport;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;

public class FlightDAO {

	@Inject
	EntityManager em;

	@Inject
	AirportDAO airportDAO;
	
	public Collection<Flight> findAll(String date, String _origin, String _destination, String departureTime) throws Exception {
		Airport origin = airportDAO.findByCity(_origin);
		Airport destination = airportDAO.findByCity(_destination);
		String day = date.substring(0, 2);
		String month = date.substring(2);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
		Root<Flight> flights = query.from(Flight.class);
		query.select(airport);
		query.where(builder.equal(flight.get("city"), city));
		return null;
	}
}
