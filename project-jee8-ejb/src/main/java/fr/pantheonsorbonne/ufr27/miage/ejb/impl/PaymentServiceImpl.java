package fr.pantheonsorbonne.ufr27.miage.ejb.impl;

import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import fr.pantheonsorbonne.ufr27.miage.dao.InvoiceDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.PaymentService;
import fr.pantheonsorbonne.ufr27.miage.exception.NoDebtException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchUserException;
import fr.pantheonsorbonne.ufr27.miage.jpa.Customer;
import fr.pantheonsorbonne.ufr27.miage.jpa.Payment;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.Ccinfo;

@Stateless
public class PaymentServiceImpl implements PaymentService {

	@Inject
	EntityManager em;

	@EJB
	InvoiceDAO invoiceDao;

	@Resource(mappedName = "jms/__defaultConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "app/jms/PaymentQueue")
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

	public int initiatePayAllDebts(Ccinfo info, int userId) throws NoDebtException, NoSuchUserException {
		try {

			double amount = invoiceDao.getUserDebt(userId);
			if (amount <= 0) {
				throw new NoDebtException();
			}
			Payment p = new Payment();
			p.setAmount(amount);
			p.setValidated(false);
			p.getInvoices().addAll(invoiceDao.getUnpaiedInvoices(userId));
			em.persist(p);

			Message message = session.createMessage();
			message.setStringProperty("ccnumber", info.getNumber());
			message.setStringProperty("date", info.getValidityDate());
			message.setIntProperty("ccv", info.getCcv());
			message.setDoubleProperty("amount", amount);
			message.setIntProperty("userId", userId);
			message.setIntProperty("paymentId", p.getId());
			messageProducer.send(message);
			return p.getId();

		} catch (JMSException e) {
			throw new RuntimeException("failed to initiate payment", e);
		}
	}

	public int initiatePayment(Ccinfo info, int userId, int invoiceId, double amount) {
		try {

			Payment p = new Payment();
			p.setAmount(amount);
			p.setValidated(false);
			em.persist(p);

			Message message = session.createMessage();
			message.setStringProperty("ccnumber", info.getNumber());
			message.setStringProperty("date", info.getValidityDate());
			message.setIntProperty("ccv", info.getCcv());
			message.setDoubleProperty("amount", amount);
			message.setIntProperty("userId", userId);
			message.setIntProperty("paymentId", p.getId());
			messageProducer.send(message);
			return p.getId();

		} catch (JMSException e) {
			throw new RuntimeException("failed to initiate payment", e);
		}
	}

}
