package bank_api.services;

import bank_api.dao.AccountDAO;
import bank_api.entity.Account;
import bank_api.entity.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private AccountDAO accountDAO;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    @Transactional
    public String checkBalance(int id) {
        logger.info("\"checkBalance(int id)\"");
        Account account = accountDAO.findById(id);
        return account.getAmount().toString();
    }

    @Override
    @Transactional
    public Account findById(int id) {
        return accountDAO.findById(id);
    }


    @Override
    @Transactional
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    @Transactional
    public void merge(Account account) {
        accountDAO.merge(account);
    }

    @Override
    @Transactional
    public List<Card> getAllCards(List<Account> accounts) {
        logger.info("\"getAllCards(List<Account> accounts)\"");
        List<Card> cards = new ArrayList<>();
        logger.info("Create card list");
        for (Account account : accounts) {
            List<Card> tempList = account.getCards();
            cards.addAll(tempList);
        }
        logger.info("Return card list");
        return cards;
    }
}
