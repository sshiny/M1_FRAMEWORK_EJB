package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.pantheonsorbonne.ufr27.miage.exception.NoAirportForSuchCityException;
import fr.pantheonsorbonne.ufr27.miage.jpa.AirportJPA;

public class AirportDAO {
	
	@Inject
	EntityManager em;
	
	public AirportJPA create(String code, String city) {
		AirportJPA airport = new AirportJPA();
		airport.setCode(code);
		airport.setCity(city);
		em.persist(airport);
		em.flush();
		return airport;
	}
	
	public AirportJPA findByCity(String city) throws NoAirportForSuchCityException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AirportJPA> query = builder.createQuery(AirportJPA.class);
		Root<AirportJPA> airports = query.from(AirportJPA.class);
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
