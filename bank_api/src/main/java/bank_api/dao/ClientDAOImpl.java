package bank_api.dao;

import bank_api.entity.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

	// define field for entitymanager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public ClientDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	@Transactional
	public List<Client> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Client> theQuery = currentSession.createQuery("from Client", Client.class);
		
		// execute query and get result list
		List<Client> clients = theQuery.getResultList();
		
		// return the results		
		return clients;
	}

	@Override
	public Client findById(int id) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get the employee
		Client client = currentSession.get(Client.class, id);

		// return the employee
		return client;
	}


	@Override
	public void save(Client client) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// save employee
		currentSession.saveOrUpdate(client);
	}


	@Override
	public void deleteById(int id) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// delete object with primary key
		Query theQuery =
				currentSession.createQuery(
						"delete from Client where id=:clientId");
		theQuery.setParameter("clientId", id);

		theQuery.executeUpdate();
	}

}







