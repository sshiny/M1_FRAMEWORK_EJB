package fr.pantheonsorbonne.ufr27.miage.jpa;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity

public class FlightJPA {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		int id;
		
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		Set<ReservationJPA> reservations = new HashSet<>();
		
		AirportJPA origin;
		AirportJPA destination;
		LocalDateTime dateAr;
		LocalDateTime dateDep;
		double prix;
		int nbPlaceA;
		int nbPlaceB;
		int nbPlaceC;
		String getAvailabilities;
		LocalDateTime duration;
	

		public LocalDateTime getDuration() {
			return duration;
		}

		public void setDuration(LocalDateTime duration) {
			this.duration = duration;
		}

		public String getGetAvailabilities() {
			getAvailabilities = "Nombre de place en A " + getNbPlaceA() + "Nombre de place en B " + getNbPlaceB() + "Nombre de place en C = " + getNbPlaceC();
			return getAvailabilities;
		}

		public void setGetAvailabilities(String getAvailabilities) {
			this.getAvailabilities = getAvailabilities;
		}

		BigInteger idCompany;
		
		
		public Set<ReservationJPA> getReservations() {
			return reservations;
		}

		public void setReservations(Set<ReservationJPA> reservations) {
			this.reservations = reservations;
		}


		
		public BigInteger getCompany() {
			return idCompany;
		}

		public void setCompany(BigInteger idCompany) {
			this.idCompany = idCompany;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
	
		
		public double getPrix() {
			return prix;
		}
		
		public void setprix(double prix) {
			this.prix = prix;
		}
		
		public int getNbPlaceA() {
			return nbPlaceA;
		}

		public void setNbPlaceA(int nbPlaceA) {
			this.nbPlaceA = nbPlaceA;
		}

		public int getNbPlaceB() {
			return nbPlaceB;
		}

		public void setNbPlaceB(int nbPlaceB) {
			this.nbPlaceB = nbPlaceB;
		}

		public int getNbPlaceC() {
			return nbPlaceC;
		}

		public void setNbPlaceC(int nbPlaceC) {
			this.nbPlaceC = nbPlaceC;
		}
		
		
		public LocalDateTime getDateAr() {
			return dateAr;
		}

		public void setDateAr(LocalDateTime dateAr) {
			this.dateAr = dateAr;
		}

		public LocalDateTime getDateDep() {
			return dateDep;
		}

		public void setDateDep(LocalDateTime dateDep) {
			this.dateDep = dateDep;
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
	
}
