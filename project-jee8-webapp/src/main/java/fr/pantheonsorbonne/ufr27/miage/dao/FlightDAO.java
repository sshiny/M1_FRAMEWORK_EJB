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

import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Airport;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;

public class FlightDAO {

	@Inject
	EntityManager em;

	@Inject
	AirportDAO airportDAO;
	
	public List<Flight> findAll(String _date, String _origin, String _destination, String departureTime) throws Exception {
		Airport origin = airportDAO.findByCity(_origin);
		Airport destination = airportDAO.findByCity(_destination);
		String day = _date.substring(0, 2);
		String month = _date.substring(2);
		
		// get current year
		Date current = new Date();
		LocalDate localDate = current.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		
		LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);
		LocalTime time = LocalTime.parse(departureTime);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
		Root<Flight> flights = query.from(Flight.class);
		query.select(flights);
		query.where(
			builder.equal(flights.get("date"), date),
			builder.equal(flights.get("origin"), origin),
			builder.equal(flights.get("destination"), destination),
			builder.equal(flights.get("departureTime"), time)
		);
		return em.createQuery(query).getResultList();
	}
}
