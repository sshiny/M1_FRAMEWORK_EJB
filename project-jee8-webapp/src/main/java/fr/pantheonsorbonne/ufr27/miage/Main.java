package fr.pantheonsorbonne.ufr27.miage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Locale;

import javax.inject.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.h2.tools.Server;
import org.slf4j.bridge.SLF4JBridgeHandler;

import fr.pantheonsorbonne.ufr27.miage.conf.EMFFactory;
import fr.pantheonsorbonne.ufr27.miage.conf.EMFactory;
import fr.pantheonsorbonne.ufr27.miage.dao.AirportDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.BookingDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FlightDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.InvoiceDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.PaymentDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.ejb.BookingService;
import fr.pantheonsorbonne.ufr27.miage.ejb.FlightService;
import fr.pantheonsorbonne.ufr27.miage.ejb.GymService;
import fr.pantheonsorbonne.ufr27.miage.ejb.InitService;
import fr.pantheonsorbonne.ufr27.miage.ejb.InitServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.ejb.InvoicingService;
import fr.pantheonsorbonne.ufr27.miage.ejb.MailingService;
import fr.pantheonsorbonne.ufr27.miage.ejb.PassengerService;
import fr.pantheonsorbonne.ufr27.miage.ejb.PaymentService;
import fr.pantheonsorbonne.ufr27.miage.ejb.impl.BookingServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.ejb.impl.FlightServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.ejb.impl.GymServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.ejb.impl.InvoicingServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.ejb.impl.MailingServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.ejb.impl.PassengerServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.ejb.impl.PaymentServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.exception.ExceptionMapper;
import fr.pantheonsorbonne.ufr27.miage.jms.PaymentValidationAckownledgerBean;
import fr.pantheonsorbonne.ufr27.miage.jms.conf.ConnectionFactorySupplier;
import fr.pantheonsorbonne.ufr27.miage.jms.conf.JMSProducer;
import fr.pantheonsorbonne.ufr27.miage.jms.conf.PaymentAckQueueSupplier;
import fr.pantheonsorbonne.ufr27.miage.jms.conf.PaymentQueueSupplier;
import fr.pantheonsorbonne.ufr27.miage.jms.payment.PaymentProcessorBean;
import fr.pantheonsorbonne.ufr27.miage.jms.utils.BrokerUtils;
import fr.pantheonsorbonne.ufr27.miage.model.jaxb.ObjectFactory;

/**
 * Main class.
 *
 */
public class Main {

	public static final String BASE_URI = "http://localhost:8080/";

	public static HttpServer startServer() {

		final ResourceConfig rc = new ResourceConfig()//
				.packages(true, "fr.pantheonsorbonne.ufr27.miage")//
				.register(DeclarativeLinkingFeature.class)//
				.register(JMSProducer.class).register(ExceptionMapper.class).register(new AbstractBinder() {

					@Override
					protected void configure() {

						bind(GymServiceImpl.class).to(GymService.class);

						bind(PaymentServiceImpl.class).to(PaymentService.class);
						bind(InvoicingServiceImpl.class).to(InvoicingService.class);
						bind(InvoiceDAO.class).to(InvoiceDAO.class);
						bind(UserDAO.class).to(UserDAO.class);
						bind(MailingServiceImpl.class).to(MailingService.class);
						bind(PaymentDAO.class).to(PaymentDAO.class);
						bindFactory(EMFFactory.class).to(EntityManagerFactory.class).in(Singleton.class);
						bindFactory(EMFactory.class).to(EntityManager.class).in(RequestScoped.class);
						bindFactory(ConnectionFactorySupplier.class).to(ConnectionFactory.class).in(Singleton.class);
						bindFactory(PaymentAckQueueSupplier.class).to(Queue.class).named("PaymentAckQueue")
								.in(Singleton.class);
						bindFactory(PaymentQueueSupplier.class).to(Queue.class).named("PaymentQueue")
								.in(Singleton.class);

						bind(PaymentProcessorBean.class).to(PaymentProcessorBean.class).in(Singleton.class);
						bind(PaymentValidationAckownledgerBean.class).to(PaymentValidationAckownledgerBean.class)
								.in(Singleton.class);

						bind(ObjectFactory.class).to(ObjectFactory.class);
						bind(BookingServiceImpl.class).to(BookingService.class);
						bind(FlightServiceImpl.class).to(FlightService.class);
						bind(PassengerServiceImpl.class).to(PassengerService.class);
						bind(InitServiceImpl.class).to(InitService.class);
						bind(AirportDAO.class).to(AirportDAO.class);
						bind(BookingDAO.class).to(BookingDAO.class);
						bind(FlightDAO.class).to(FlightDAO.class);
						bind(PassengerDAO.class).to(PassengerDAO.class);
					}

				});

		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	/**
	 * Main method.beanbeanbeanbean
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Locale.setDefault(Locale.ENGLISH);
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		final HttpServer server = startServer();

		BrokerUtils.startBroker();

		startH2Console();

		System.out.println(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();
		server.stop();

	}

	private static void startH2Console() {
		Server server;
		try {
			server = Server.createWebServer("-webAllowOthers");

			server = server.start();

			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				Desktop.getDesktop().browse(new URI("http://localhost:8082"));

			}
		} catch (SQLException | IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
