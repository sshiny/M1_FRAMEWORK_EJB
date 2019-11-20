package fr.pantheonsorbonne.ufr27.miage.service.impl;

import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;

@MessageDriven(mappedName = "app/jms/PaymentQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class PaymentProcessorBean implements MessageListener {

	@Resource(mappedName = "jms/__defaultConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "app/jms/PaymentAckQueue")
	private Queue queue;

	private Connection connection;

	private Session session;

	private MessageProducer messageProducer;

	@PostConstruct
	private void init() {

		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
		} catch (JMSException e) {
			throw new RuntimeException("failed to create JMS Session", e);
		}

	}

	@Override
	public void onMessage(Message message) {
		try {
			String ccnumber = message.getStringProperty("ccnumber");
			String date = message.getStringProperty("date");
			int ccv = message.getIntProperty("ccv");
			double amount = message.getDoubleProperty("amount");
			int paymentId = message.getIntProperty("paymentId");

			if (isPaymentValdated(ccnumber, date, ccv, amount)) {
				Message outgoingMessage = this.session.createMessage();
				outgoingMessage.setIntProperty("paymentId", paymentId);
				outgoingMessage.setBooleanProperty("validated", true);
				messageProducer.send(outgoingMessage);
			}

		} catch (JMSException e) {
			throw new RuntimeException("failed to process payment", e);
		}

	}

	private boolean isPaymentValdated(String ccnumber, String date, int ccv, double amount) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// dummy implementation
		return true;

	}

}
