package bank_api.dao;

import java.util.List;

import javax.persistence.EntityManager;

import bank_api.entity.Account;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDAOImpl implements AccountDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public AccountDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	@Transactional
	public List<Account> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Account> theQuery =
				currentSession.createQuery("from Account", Account.class);
		
		// execute query and get result list
		List<Account> accounts = theQuery.getResultList();
		
		// return the results		
		return accounts;
	}

	@Override
	public Account findById(int id) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get the employee
		Account account =
				currentSession.get(Account.class, id);

		// return the employee
		return account;
	}


	@Override
	public void save(Account account) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// save employee
		currentSession.saveOrUpdate(account);
	}


	@Override
	public void deleteById(int id) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// delete object with primary key
		Query theQuery =
				currentSession.createQuery(
						"delete from Account where id=:accountId");
		theQuery.setParameter("accountId", id);

		theQuery.executeUpdate();
	}

}







