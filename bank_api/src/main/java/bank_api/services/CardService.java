package bank_api.services;

import bank_api.entity.Account;
import bank_api.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CardService {

    public List<Card> findAll();

    public Card findById(int id);

    public List<Card> findByAccountId(int accountId);

    public void save(Card card);

    public void deleteById(int id);

    public String getExpirationDate();

    public Long getNewCardNumber();

    public int generateCvv();

    public Card createCard(Account account);
}
