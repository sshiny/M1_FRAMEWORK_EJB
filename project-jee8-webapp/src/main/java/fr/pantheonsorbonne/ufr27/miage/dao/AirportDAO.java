package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.pantheonsorbonne.ufr27.miage.exception.NoAirportForSuchCityException;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Airport;

public class AirportDAO {
	
	@Inject
	EntityManager em;
	
	public Airport findByCity(String city) throws NoAirportForSuchCityException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Airport> query = builder.createQuery(Airport.class);
		Root<Airport> airports = query.from(Airport.class);
		query.select(airports);
		query.where(builder.equal(airports.get("city"), city));
		try {
			return em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			if (e instanceof NoResultException) {
				throw new NoAirportForSuchCityException();
			}
			throw e;
		}
	}
}
