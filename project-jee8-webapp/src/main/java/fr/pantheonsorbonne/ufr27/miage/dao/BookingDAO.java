package fr.pantheonsorbonne.ufr27.miage.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.ReservationJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AirportJPA;

public class BookingDAO {
	@Inject
	EntityManager em;

	@Inject
	AirportDAO airportDAO;
	
	public List<ReservationJPA> findAll(int _flyId, int _passengerId) throws Exception {
		
		
		
		
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ReservationJPA> query = builder.createQuery(ReservationJPA.class);
		Root<ReservationJPA> booking = query.from(ReservationJPA.class);
		query.select(booking);
		query.where(	
			builder.equal(booking.get("vol"), _flyId),
			builder.equal(booking.get("origin"), _passengerId)
		);
		return em.createQuery(query).getResultList();
	}
}
