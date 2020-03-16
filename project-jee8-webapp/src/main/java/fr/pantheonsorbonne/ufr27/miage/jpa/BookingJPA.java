package fr.pantheonsorbonne.ufr27.miage.jpa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookingJPA {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;
	
	double feePerPassenger;
	FlightJPA flight;
	Set<PassengerJPA> passengers = new HashSet<>();
	Map<String, Integer> seats = new HashMap<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getFeePerPassenger() {
		return feePerPassenger;
	}

	public void setFeePerPassenger(double feePerPassenger) {
		this.feePerPassenger = feePerPassenger;
	}

	public FlightJPA getFlight() {
		return flight;
	}

	public void setFlight(FlightJPA flight) {
		this.flight = flight;
	}

	public Set<PassengerJPA> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<PassengerJPA> passengers) {
		this.passengers = passengers;
	}

	public Map<String, Integer> getSeats() {
		return seats;
	}

	public void setSeats(Map<String, Integer> seats) {
		this.seats = seats;
	}
}
