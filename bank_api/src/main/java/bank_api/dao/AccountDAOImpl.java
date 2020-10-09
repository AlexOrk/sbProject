package bank_api.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import bank_api.entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager entityManager;

    @Autowired
    public AccountDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public List<Account> findAll() {
        logger.info("\"findAll()\"");
        logger.info("Get the current hibernate session");
        Session currentSession = entityManager.unwrap(Session.class);

        logger.info("Create a query");
        Query<Account> theQuery =
                currentSession.createQuery("from Account", Account.class);

        logger.info("Execute query and get accounts list");
        List<Account> accounts = theQuery.getResultList();

        logger.info("return accounts");
        return accounts;
    }

    @Override
    public Account findById(int id) {
        logger.info("\"findById(int id)\"");
        logger.info("Get the current hibernate session");
        Session currentSession = entityManager.unwrap(Session.class);

        logger.info("Get an account");
        Account account = currentSession.get(Account.class, id);

        logger.info("Return the account");
        return account;
    }

    @Override
    public void merge(Account account) {
        logger.info("\"merge(Account account)\"");
        logger.info("Get the current hibernate session");
        Session currentSession = entityManager.unwrap(Session.class);

        logger.info("Save the account");
        currentSession.merge(account);
    }
}







