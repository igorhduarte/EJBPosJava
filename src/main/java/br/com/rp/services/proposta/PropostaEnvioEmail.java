package br.com.rp.services.proposta;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@MessageDriven
public class PropostaEnvioEmail implements MessageListener {

	@Resource
	private ConnectionFactory connectionFactory;

	@Resource(name = "PropostaEmailQueue")
	private Queue propostaEmailQueue;

	@PersistenceContext()
	EntityManager entityManager;

	@Override
	public void onMessage(Message message) {
		try {

			respond("Proposta aceita");
		} catch (JMSException ex) {
			
			throw new RuntimeException(ex);
		}
	}

	private void respond(String text) throws JMSException {

		Connection connection = null;
		Session session = null;

		try {
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer producer = session.createProducer(propostaEmailQueue);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.createTextMessage(text);

			producer.send(message);
		} finally {
			if (session != null)
				session.close();
			if (connection != null)
				connection.close();
		}
	}
}
