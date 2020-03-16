package fr.pantheonsorbonne.ufr27.miage.jpa;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class FlightJPA {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	String company;
	double price;
	int nbPlacesA;
	int nbPlacesB;
	int nbPlacesC;
	AirportJPA origin;
	AirportJPA destination;
	LocalDateTime departureDt;
	LocalDateTime arrivalDt;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<BookingJPA> bookings = new HashSet<>();

	public String getAvailabilities() {
		return "A" + nbPlacesA + "B" + nbPlacesB + "C" + nbPlacesC;
	}

	public int getNbPlacesTotal() {
		return nbPlacesA + nbPlacesB + nbPlacesC;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNbPlacesA() {
		return nbPlacesA;
	}

	public void setNbPlacesA(int nbPlacesA) {
		this.nbPlacesA = nbPlacesA;
	}

	public int getNbPlacesB() {
		return nbPlacesB;
	}

	public void setNbPlacesB(int nbPlacesB) {
		this.nbPlacesB = nbPlacesB;
	}

	public int getNbPlacesC() {
		return nbPlacesC;
	}

	public void setNbPlacesC(int nbPlacesC) {
		this.nbPlacesC = nbPlacesC;
	}

	public AirportJPA getOrigin() {
		return origin;
	}

	public void setOrigin(AirportJPA origin) {
		this.origin = origin;
	}

	public AirportJPA getDestination() {
		return destination;
	}

	public void setDestination(AirportJPA destination) {
		this.destination = destination;
	}

	public LocalDateTime getDepartureDt() {
		return departureDt;
	}

	public void setDepartureDt(LocalDateTime departureDt) {
		this.departureDt = departureDt;
	}

	public LocalDateTime getArrivalDt() {
		return arrivalDt;
	}

	public void setArrivalDt(LocalDateTime arrivalDt) {
		this.arrivalDt = arrivalDt;
	}

	public Set<BookingJPA> getBookings() {
		return bookings;
	}

	public void setBookings(Set<BookingJPA> bookings) {
		this.bookings = bookings;
	}
}
