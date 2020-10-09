package bank_api.dao;

import bank_api.entity.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public ClientDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	@Transactional
	public List<Client> findAll() {
		logger.info("\"findAll()\"");
		logger.info("Get the current hibernate session");
		Session currentSession = entityManager.unwrap(Session.class);

		logger.info("Create a query");
		Query<Client> theQuery = currentSession.createQuery("from Client", Client.class);

		logger.info("Execute query and get clients list");
		List<Client> clients = theQuery.getResultList();

		logger.info("return clients");
		return clients;
	}

	@Override
	public Client findById(int id) {
		logger.info("\"findById(int id)\"");
		logger.info("Get the current hibernate session");
		Session currentSession = entityManager.unwrap(Session.class);

		logger.info("Get a client");
		Client client = currentSession.get(Client.class, id);

		logger.info("Return the client");
		return client;
	}

}







