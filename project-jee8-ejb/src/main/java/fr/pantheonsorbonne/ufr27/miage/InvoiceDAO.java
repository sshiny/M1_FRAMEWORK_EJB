package fr.pantheonsorbonne.ufr27.miage;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Contract;
import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Customer;
import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Invoice;

@Stateless
public class InvoiceDAO {

	@Inject
	EntityManager em;

	public Collection<Invoice> getUnpaiedInvoices(int userId) throws NoSuchUserException {

		Customer customer = em.find(Customer.class, userId);
		if(customer==null) {
			throw new NoSuchUserException();
		}
		return customer.getContracts().//
				stream().//
				map(Contract::getInvoices).//
				flatMap(Collection::stream).//
				filter(i -> !i.isPayed()).//
				collect(Collectors.toSet());
	}

	public double getUserDebt(int userId) throws NoSuchUserException {

		return this.getUnpaiedInvoices(userId).stream().//
				map(i -> i.getContract().getMonthlyFare()).//
				collect(Collectors.summingDouble(Double::doubleValue));
	}

}
