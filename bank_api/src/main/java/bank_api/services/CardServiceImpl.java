package bank_api.services;

import bank_api.dao.CardDAO;
import bank_api.entity.Account;
import bank_api.entity.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private CardDAO cardDAO;

    @Autowired
    public CardServiceImpl(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @Override
    @Transactional
    public List<Card> findAll() {
        return cardDAO.findAll();
    }

    @Override
    @Transactional
    public Card findById(int id) {
        return cardDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Card> findByAccountId(int accountId) {
        return cardDAO.findByAccountId(accountId);
    }


    @Override
    @Transactional
    public void save(Card card) {
        cardDAO.save(card);
    }


    @Override
    @Transactional
    public void deleteById(int id) {
        cardDAO.deleteById(id);
    }

    @Override
    @Transactional
    public String getExpirationDate() {
        logger.info("\"getExpirationDate()\"");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 365 * 3);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/yy");

        logger.info("Return new expiration date");
        return formatForDateNow.format(cal.getTime());
    }

    @Override
    @Transactional
    public Long getNewCardNumber() {
        logger.info("\"getNewCardNumber()\"");
        long number = 0;
        List<Card> cards = cardDAO.findAll();
        if (cards.size() == 0) {
            number = 4276550100000001L;
        } else {
            number = cards.get(cards.size() - 1).getNumber() + 1;
        }
        logger.info("Return new card number");
        return number;
    }

    @Override
    @Transactional
    public int generateCvv() {
        return new Random().nextInt(999);
    }

    @Override
    @Transactional
    public Card createCard(Account account) {
        logger.info("\"createCard(Account account)\"");
        long number = getNewCardNumber();
        int cvv = generateCvv();
        String date = getExpirationDate();

        logger.info("Return new card");
        return new Card(number, date, cvv, account);
    }
}
