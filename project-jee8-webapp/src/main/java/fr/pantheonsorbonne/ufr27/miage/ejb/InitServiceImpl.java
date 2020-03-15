package fr.pantheonsorbonne.ufr27.miage.ejb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.AirportDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.jpa.AirportJPA;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;

public class InitServiceImpl implements InitService {
	
	@Inject
	EntityManager em;
	
	@Inject
	FlightDAO flightDao;
	
	@Inject
	AirportDAO AirportDao;
	
	private FlightJPA createFlight(String company, double price, int nbPlaces, AirportJPA origin, AirportJPA destination, int hour) {
		LocalTime from = LocalTime.of(hour, 0);
		LocalTime to = from.plusHours(1).plusMinutes(30);
		LocalDateTime departure = LocalDateTime.of(LocalDate.now(), from); 
		LocalDateTime arrival = LocalDateTime.of(LocalDate.now(), to);
		
		FlightJPA f = new FlightJPA();
		f.setCompany(company);
		f.setPrice(price);
		f.setNbPlacesA(nbPlaces);
		f.setNbPlacesB(nbPlaces);
		f.setNbPlacesC(nbPlaces);
		f.setOrigin(origin);
		f.setDestination(destination);
		f.setDepartureDt(departure);
		f.setArrivalDt(arrival);
		return f;
	}

	@Override
	public void init() {
		em.getTransaction().begin();
		String company = "AF";
		double price = 100;
		int nbPlaces = 30;
		
		AirportJPA origin = new AirportJPA();
		origin.setCode("CDG");
		origin.setCity("PAR");
		em.persist(origin);
		
		AirportJPA destination = new AirportJPA();
		destination.setCode("BDX");
		destination.setCity("BDX");
		em.persist(destination);
		
		FlightJPA f = createFlight(company, price, nbPlaces, origin, destination, 8); // 8h flight
		em.persist(f);
		
		f = createFlight(company, price, nbPlaces, origin, destination, 12); // 12h flight
		em.persist(f);
		
		f = createFlight(company, price, nbPlaces, origin, destination, 14); // 14h flight
		em.persist(f);
		
		em.getTransaction().commit();
	}

}
