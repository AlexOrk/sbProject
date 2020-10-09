package bank_api.dao;

import bank_api.entity.Card;
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
public class CardDAOImpl implements CardDAO {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private EntityManager entityManager;

	@Autowired
	public CardDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}


	@Override
	@Transactional
	public List<Card> findAll() {
		logger.info("\"findAll()\"");
		logger.info("Get the current hibernate session");
		Session currentSession = entityManager.unwrap(Session.class);

		logger.info("Create a query");
		Query<Card> theQuery =
				currentSession.createQuery("from Card", Card.class);

		logger.info("Execute query and get cards list");
		List<Card> cards = theQuery.getResultList();

		logger.info("return cards");
		return cards;
	}

	@Override
	public Card findById(int id) {
		logger.info("\"findById(int id)\"");
		logger.info("Get the current hibernate session");
		Session currentSession = entityManager.unwrap(Session.class);

		logger.info("Get a card");
		Card card = currentSession.get(Card.class, id);

		logger.info("Return the card");
		return card;
	}



	@Override
	public void save(Card card) {
		logger.info("\"save(Card card)\"");
		logger.info("Get the current hibernate session");
		Session currentSession = entityManager.unwrap(Session.class);

		logger.info("Save the card");
		currentSession.saveOrUpdate(card);
	}

}







