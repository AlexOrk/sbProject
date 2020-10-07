package bank_api.dao;

import bank_api.entity.Card;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CardDAOImpl implements CardDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public CardDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public List<Card> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Card> theQuery =
                currentSession.createQuery("from Card", Card.class);

        // execute query and get result list
        List<Card> cards = theQuery.getResultList();

        // return the results
        return cards;
    }

    @Override
    public Card findById(int id) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        Card card =
                currentSession.get(Card.class, id);

        // return the employee
        return card;
    }


    @Override
    public void save(Card card) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save employee
        currentSession.saveOrUpdate(card);
    }


    @Override
    public void deleteById(int id) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery(
                        "delete from Card where id=:cardId");
        theQuery.setParameter("cardId", id);

        theQuery.executeUpdate();
    }

    @Override
    public int getAmountCards() {

        return (int) 1;
    }

//    @Override
//    public Card getLastCard() {
//        Session currentSession = entityManager.unwrap(Session.class);
//
//        Query theQuery = currentSession.createQuery("from card order by id DESC");
//        theQuery.setMaxResults(1);
//        return (Card) theQuery.uniqueResult();
//    }
}







