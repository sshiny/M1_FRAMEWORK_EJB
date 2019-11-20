package fr.pantheonsorbonne.ufr27.miage.jms;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;

import fr.pantheonsorbonne.ufr27.miage.jpa.Invoice;
import fr.pantheonsorbonne.ufr27.miage.jpa.Payment;

public class PaymentValidationAckownledgerBean implements MessageListener {

	@Inject
	EntityManager em;

	@Inject
	private ConnectionFactory connectionFactory;

	@Inject
	@Named("PaymentAckQueue")
	private Queue queueAck;

	private Connection connection;

	private Session session;

	private MessageConsumer consumer;

	@PostConstruct
	private void init() {

		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			consumer = session.createConsumer(queueAck);
			
			MessageListener listener = this;

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Message message = consumer.receive();
							listener.onMessage(message);
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}).start();

		} catch (JMSException e) {
			throw new RuntimeException("failed to create JMS Session", e);
		}

	}

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
