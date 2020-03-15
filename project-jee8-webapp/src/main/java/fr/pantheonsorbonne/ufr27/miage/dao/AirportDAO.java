package fr.pantheonsorbonne.ufr27.miage.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Airport;

public class AirportDAO {
	
	@Inject
	EntityManager em;
	
	public Airport findByCity(String city) throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Airport> query = builder.createQuery(Airport.class);
		Root<Airport> airport = query.from(Airport.class);
		query.select(airport);
		query.where(builder.equal(airport.get("city"), city));
		return em.createQuery(query).getSingleResult();
	}
}
