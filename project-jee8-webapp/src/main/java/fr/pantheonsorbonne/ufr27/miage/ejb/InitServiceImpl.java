package fr.pantheonsorbonne.ufr27.miage.ejb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.AirportDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.jpa.AirportJPA;

public class InitServiceImpl implements InitService {
	
	@Inject
	EntityManager em;
	
	@Inject
	FlightDAO flightDao;
	
	@Inject
	AirportDAO airportDao;
	
	private void createFlight(String company, double price, int nbPlaces, AirportJPA origin, AirportJPA destination, int hour) {
		LocalTime from = LocalTime.of(hour, 0);
		LocalTime to = from.plusHours(1).plusMinutes(30);
		LocalDateTime departure = LocalDateTime.of(LocalDate.now(), from); 
		LocalDateTime arrival = LocalDateTime.of(LocalDate.now(), to);
		
		flightDao.create(company, price, nbPlaces, origin, destination, departure, arrival);
	}

	@Override
	public void init() {
		String company = "AF";
		double price = 100;
		int nbPlaces = 30;
		
		AirportJPA origin = airportDao.create("CDG", "PAR");
		AirportJPA destination = airportDao.create("BDX", "BDX");
		
		createFlight(company, price, nbPlaces, origin, destination, 8); // 8h flight
		createFlight(company, price, nbPlaces, origin, destination, 12); // 12h flight
		createFlight(company, price, nbPlaces, origin, destination, 14); // 14h flight
	}
}
