package fr.pantheonsorbonne.ufr27.miage.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ReservationJPA {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		FlightJPA fly;
		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		PassagerJPA passenger;
		public PassagerJPA getPassenger() {
			return passenger;
		}

		public void setPassenger(PassagerJPA passenger) {
			this.passenger = passenger;
		}
		int id;
		double feePerPassenger;
		
		public FlightJPA getFly() {
			return fly;
		}

		public void setFly(FlightJPA fly) {
			this.fly = fly;
		}

		public int getid() {
			return id;
		}
		
		public void setId(int code) {
			this.id = id;
		} 
		
		public double getFeePerPassenger() {
			return feePerPassenger;
		}
		public void setFeePerPassenger(double feePerPassenger) {
			this.feePerPassenger = feePerPassenger;
		}
		
}
