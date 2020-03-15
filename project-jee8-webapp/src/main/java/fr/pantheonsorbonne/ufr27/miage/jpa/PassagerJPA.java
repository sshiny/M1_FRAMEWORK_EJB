package fr.pantheonsorbonne.ufr27.miage.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PassagerJPA {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String nom;
	String prenom;
	
	
	public int getid() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	} 
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
}
