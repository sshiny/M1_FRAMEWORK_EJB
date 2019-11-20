package fr.pantheonsorbonne.ufr27.miage;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Contract;
import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Customer;
import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Invoice;

@Stateless
public class InvoicingServiceImpl implements InvoicingService {

	@Inject
	EntityManager em;
	
	@EJB
	MailingService ms;

	@Override
	public void sendNextInvoice(int customerId) {

		Customer customer = em.find(Customer.class, customerId);
		LocalDateTime today = LocalDateTime.now();
		Set<Contract> contracts = customer.getContracts();
		for (Contract contract : contracts) {
			boolean isInvoiceNeeded = true;
			if (contract.getEnDate() == null || contract.getEnDate().after(new Date())) {
				for (Invoice invoice : contract.getInvoices()) {
					if (invoice.getDate().toInstant().compareTo(today.toInstant(ZoneOffset.UTC)) > 0) {
						isInvoiceNeeded = false;
						break;
					}
				}
				if (isInvoiceNeeded) {
					Invoice invoice = new Invoice();
					invoice.setContract(contract);
					invoice.setDate(Date.from(today.plusMonths(1).toInstant(ZoneOffset.UTC)));
					em.persist(invoice);
					contract.getInvoices().add(invoice);
					
				}
			}
			em.merge(contract);
		}

	}

}
