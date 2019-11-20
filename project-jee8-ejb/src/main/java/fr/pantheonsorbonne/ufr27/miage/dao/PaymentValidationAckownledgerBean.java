package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Invoice;
import fr.pantheonsorbonne.ufr27.miage.dao.entitiy.Payment;

@MessageDriven(mappedName = "app/jms/PaymentAckQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class PaymentValidationAckownledgerBean implements MessageListener {

	@Inject
	EntityManager em;

	@Override
	public void onMessage(Message message) {
		try {
			int paymentId = message.getIntProperty("paymentId");
			boolean b = message.getBooleanProperty("validated");
			if (b) {
				Payment payment = em.find(Payment.class, paymentId);
				payment.setValidated(true);
				em.merge(payment);
				for (Invoice invoice : payment.getInvoices()) {
					invoice.setPayed(true);
					em.merge(invoice);
				}
			}
		} catch (JMSException e) {
			throw new RuntimeException("failed to validate payment ", e);
		}

	}

}
