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

	// #####################################
	// Тот же вопрос, что и в аккаунт ДАО
	// Точно ли нужен этот метод?
	// Можно найти карты через аккаунт ДАО
	// #####################################
    @Override
    public List<Card> findByAccountId(int accountId) {
		logger.info("\"findByAccountId(int accountId)\"");
		logger.info("Get the current hibernate session");
        Session currentSession = entityManager.unwrap(Session.class);

		logger.info("Create a query");
        Query<Card> theQuery = currentSession.createQuery(
                        "from Card where account_id=:accountId",Card.class);
        theQuery.setParameter("accountId", accountId);

		logger.info("Get cards");
        List<Card> cards = theQuery.getResultList();

		logger.info("Return cards");
        return cards;
    }


	@Override
	public void save(Card card) {
		logger.info("\"save(Card card)\"");
		logger.info("Get the current hibernate session");
		Session currentSession = entityManager.unwrap(Session.class);

		logger.info("Save the card");
		currentSession.saveOrUpdate(card);
	}


	@Override
	public void deleteById(int id) {
		logger.info("\"deleteById(int id)\"");
		logger.info("Get the current hibernate session");
		Session currentSession = entityManager.unwrap(Session.class);

		logger.info("Create a query");
		Query theQuery =
				currentSession.createQuery(
						"delete from Card where id=:cardId");
		theQuery.setParameter("cardId", id);

		logger.info("Delete object with primary key");
		theQuery.executeUpdate();
	}
}







