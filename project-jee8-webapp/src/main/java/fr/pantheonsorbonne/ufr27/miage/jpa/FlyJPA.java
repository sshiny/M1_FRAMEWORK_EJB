package fr.pantheonsorbonne.ufr27.miage.jpa;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class FlyJPA {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		int id;
		
		
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
		AirportJPA airport;

		Date dtDepart;
		Date dtArrive;
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
		
		public Date getDtDepart() {
			return dtDepart;
		}
		public void setDtDepart(Date dtDepart) {
			this.dtDepart = dtDepart	;
		}
		
		public Date getDtArrive() {
			return dtArrive;
		}
		public void setdtArrive(Date dtArrive) {
			this.dtArrive = dtArrive	;
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
			return airport;
		}	

		
		
		
	

	
}
