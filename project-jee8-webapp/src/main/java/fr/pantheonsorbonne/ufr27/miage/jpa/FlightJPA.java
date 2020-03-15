package fr.pantheonsorbonne.ufr27.miage.jpa;
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
		public Set<ReservationJPA> getReservations() {
			return reservations;
		}

		public void setReservations(Set<ReservationJPA> reservations) {
			this.reservations = reservations;
		}

		AirportJPA origin;
		AirportJPA destination;

		LocalDateTime dateAr;
		LocalDateTime date;
		double prix;
		int nbPlaceA;
		int nbPlaceB;
		int nbPlaceC;
		String company;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public LocalDateTime getDate() {
			return getDate();
		}
		public void setDtDepart(LocalDateTime date) {
			this.date = date;
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
		
		public AirportJPA getAddress() {
			return origin;
		}	

		
		
		
	

	
}
