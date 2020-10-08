package bank_api.dao;

import java.util.List;

import javax.persistence.EntityManager;

import bank_api.entity.Account;
import org.hibernate.Session;
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

    // ####################################################
    // Не ясно, зачем нужен этот метод? Если нужен список аккаунтов конкретного клиента,
    // то можно просто обратиться к clientService.findById().getAccounts()
    // ####################################################
    @Override
    public List<Account> findByCliendId(int clientId) { // Опечатка
        logger.info("\"findByCliendId(int clientId)\"");
        logger.info("Get the current hibernate session");
        Session currentSession = entityManager.unwrap(Session.class);

        logger.info("Create a query");
        Query<Account> theQuery =
                currentSession.createQuery(
                        "from Account where client_id=:clientId", Account.class);
        theQuery.setParameter("clientId", clientId);

        logger.info("Get accounts");
        List<Account> accounts = theQuery.getResultList();

        logger.info("Return accounts");
        return accounts;
    }


    @Override
    public void save(Account account) {
        logger.info("\"Save(Account account)\"");
        logger.info("Get the current hibernate session");
        Session currentSession = entityManager.unwrap(Session.class);

        logger.info("Save the account");
        currentSession.saveOrUpdate(account);
    }

    @Override
    public void merge(Account account) {
        logger.info("\"merge(Account account)\"");
        logger.info("Get the current hibernate session");
        Session currentSession = entityManager.unwrap(Session.class);

        logger.info("Save the account");
        currentSession.merge(account);
    }


    @Override
    public void deleteById(int id) {
        logger.info("\"deleteById(int id)\"");
        logger.info("\"Get the current hibernate session\"");
        Session currentSession = entityManager.unwrap(Session.class);

        logger.info("Delete object with primary key");
        Query theQuery =
                currentSession.createQuery(
                        "delete from Account where id=:accountId");
        theQuery.setParameter("accountId", id);

        logger.info("Delete the object");
        theQuery.executeUpdate();
    }

}







