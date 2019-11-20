package fr.pantheonsorbonne.ufr27.miage;

import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Invoice;

public interface MailingService {

	public void sendInvoice(Invoice invoice);

}
