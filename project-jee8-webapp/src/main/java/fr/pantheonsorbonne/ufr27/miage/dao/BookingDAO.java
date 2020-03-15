package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.jpa.BookingJPA;

public class BookingDAO {
	
	@Inject
	EntityManager em;

	@Inject
	FlightDAO flightDao;
	
	public BookingJPA create() {
		em.getTransaction().begin();
		BookingJPA booking = new BookingJPA();
		em.persist(booking);
		em.getTransaction().commit();
		return booking;
	}
	
	public BookingJPA update(int flightId) {
		BookingJPA booking = new BookingJPA();
		em.merge(booking);
		return booking;
	}
}
