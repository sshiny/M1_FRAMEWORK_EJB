package fr.pantheonsorbonne.ufr27.miage.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchFlightException;
import fr.pantheonsorbonne.ufr27.miage.jpa.AirportJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;

public class FlightDAO {

	@Inject
	EntityManager em;

	@Inject
	AirportDAO airportDAO;
	
	public FlightJPA create(String company, double price, int nbPlaces, AirportJPA origin, AirportJPA destination, LocalDateTime departureDt, LocalDateTime arrivalDt) {
		em.getTransaction().begin();
		FlightJPA flight = new FlightJPA();
		flight.setCompany(company);
		flight.setPrice(price);
		flight.setNbPlacesA(nbPlaces);
		flight.setNbPlacesB(nbPlaces);
		flight.setNbPlacesC(nbPlaces);
		flight.setOrigin(origin);
		flight.setDestination(destination);
		flight.setDepartureDt(departureDt);
		flight.setArrivalDt(arrivalDt);
		em.persist(flight);
		em.getTransaction().commit();
		return flight;
	}
	
	public FlightJPA updateSeats(int id, int seats, String klass) throws NoSuchFlightException {
		em.getTransaction().begin();
        FlightJPA flight = em.find(FlightJPA.class, id);
        if (flight == null) {
        	throw new NoSuchFlightException();
        }
        switch (klass) {
	        case "A":
	        	flight.setNbPlacesA(flight.getNbPlacesA() - seats);
	        	break;
	        case "B":
	        	flight.setNbPlacesB(flight.getNbPlacesB() - seats);
	        	break;
	        case "C":
	        	flight.setNbPlacesC(flight.getNbPlacesC() - seats);
	        	break;
	        default:
	        	break;
        }
        em.persist(flight);
		em.getTransaction().commit();
        return flight;
	}
	
	public List<FlightJPA> findAll(String _date, String _origin, String _destination, String departureTime) throws Exception {
		AirportJPA origin = airportDAO.findByCity(_origin);
		AirportJPA destination = airportDAO.findByCity(_destination);
		String day = _date.substring(0, 2);
		String month = _date.substring(2);
		int year  = LocalDate.now().getYear();
		LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);
		LocalTime time = LocalTime.parse(departureTime);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<FlightJPA> query = builder.createQuery(FlightJPA.class);
		Root<FlightJPA> flights = query.from(FlightJPA.class);
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
