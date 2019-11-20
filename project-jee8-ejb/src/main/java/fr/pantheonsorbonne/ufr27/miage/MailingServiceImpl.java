package fr.pantheonsorbonne.ufr27.miage;

import javax.ejb.Stateless;

import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Address;
import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Invoice;

@Stateless
public class MailingServiceImpl implements MailingService {

	@Override
	public void sendInvoice(Invoice invoice) {
		Address address = invoice.getContract().getCustomer().getAddress();

		System.out.println("sending " + invoice.toString() + " to " + address.toString());

	}

}
