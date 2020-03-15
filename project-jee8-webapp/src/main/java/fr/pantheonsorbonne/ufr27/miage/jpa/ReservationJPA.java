package fr.pantheonsorbonne.ufr27.miage.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ReservationJPA {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
		FlyJPA fly;
		PassagerJPA passager;

		int id;
		double feePerPassenger;
		
		
		public FlyJPA getFly() {
			return fly;
		}

		public void setFly(FlyJPA fly) {
			this.fly = fly;
		}

		public int getid() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		} 
		
		public double getFeePerPassenger() {
			return feePerPassenger;
		}
		public void setFeePerPassenger(double feePerPassenger) {
			this.feePerPassenger = feePerPassenger;
		}
		
		public PassagerJPA getPassager() {
			return passager;
		}

		public void setPassager(PassagerJPA passager) {
			this.passager = passager;
		}
}
