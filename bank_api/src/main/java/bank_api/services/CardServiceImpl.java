package bank_api.services;

import bank_api.dao.AccountDAO;
import bank_api.dao.CardDAO;
import bank_api.entity.Account;
import bank_api.entity.Card;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {

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
    public Card findById(int id) {
        return cardDAO.findById(id);
    }


    @Override
    public void save(Card card) {
        cardDAO.save(card);
    }


    @Override
    public void deleteById(int id) {
        cardDAO.deleteById(id);
    }

    @Override
    public String getExpirationDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 365 * 3);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/yy");

        return formatForDateNow.format(cal.getTime());
    }

    @Override
    public Long getNewCardNumber() {
        long number = 0;
        List<Card> cards = cardDAO.findAll();
        if (cards.size() == 0) {
            number = 4276550100000001L;
        } else {
            number = cards.get(cards.size() - 1).getNumber() + 1;
        }
        return number;
    }

    @Override
    public int generateCvv() {
        return new Random().nextInt(999);
    }
}
