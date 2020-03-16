package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.FlightService;
import fr.pantheonsorbonne.ufr27.miage.jpa.FlightJPA;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Airport;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.AvailabilityNeutralRequest;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Flight;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;

public class FlightServiceImpl implements FlightService {
	
	@Inject
	FlightDAO dao;
	
	@Inject
	ObjectFactory factory;
	
	@Override
	public List<Flight> getAll(AvailabilityNeutralRequest request) throws Exception {
		List<FlightJPA> flights = dao.findAll(
			request.getDate(),
			request.getOrigin(),
			request.getDestination(),
			request.getDepartureTime()
		);
		List<Flight> res = new ArrayList<>();
		int i = 0;
		for (FlightJPA f : flights) {
			Flight flight = factory.createFlight();
			flight.setId(BigInteger.valueOf(++i));
		    flight.setCompany(f.getCompany());
		    flight.setAvailability(f.getAvailabilities());
		    
		    Airport ap = factory.createAirport();
		    ap.setCode(f.getOrigin().getCode());
		    ap.setCity(f.getOrigin().getCity());
		    flight.setOrigin(ap);

		    ap = factory.createAirport();
		    ap.setCode(f.getDestination().getCode());
		    ap.setCity(f.getDestination().getCity());
		    flight.setDestination(ap);
		    
		    LocalDateTime date = f.getDepartureDt();
		    GregorianCalendar gcal = GregorianCalendar.from(date.atZone(ZoneId.systemDefault()));
		    XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		    flight.setDepartureTime(xcal);
		    
		    date = f.getArrivalDt();
		    gcal = GregorianCalendar.from(date.atZone(ZoneId.systemDefault()));
		    xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		    flight.setArrivalTime(xcal);
		    
		    Duration duration = Duration.between(f.getDepartureDt(), f.getArrivalDt());
		    flight.setDuration(DatatypeFactory.newInstance().newDuration(duration.toString()));
			res.add(flight);
		}
		return res;
	}
}	
