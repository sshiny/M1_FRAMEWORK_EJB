package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Payment;

import javax.inject.Inject;

@Stateless
public class PaymentDAO {

	@Inject
	EntityManager manager;

	public boolean isPaymentValidated(int paymentId) {
		return manager.find(Payment.class, paymentId).isValidated();
	}

}
