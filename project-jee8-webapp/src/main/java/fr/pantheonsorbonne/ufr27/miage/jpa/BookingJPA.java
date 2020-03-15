package fr.pantheonsorbonne.ufr27.miage.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BookingJPA {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;
	
	double feePerPassenger;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	FlightJPA flight;
	
	Set<PassengerJPA> passengers = new HashSet<>();

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

	public Set<PassengerJPA> getPassenger() {
		return passengers;
	}

	public void setPassenger(Set<PassengerJPA> passengers) {
		this.passengers = passengers;
	}
}
