package bank_api.services;

import bank_api.entity.Account;
import bank_api.entity.Card;

import java.util.List;

public interface AccountService {
    public String checkBalance(int id);

//    public void deposit(int id, String sum);

    public Account findById(int id);

//    public List<Account> findByClientId(int clientId);

    public List<Account> findAll();

//    public void save(Account account);

    public void merge(Account account);

    public List<Card> getAllCards(List<Account> accounts);
}
