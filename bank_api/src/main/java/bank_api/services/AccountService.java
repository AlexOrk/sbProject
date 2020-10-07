package bank_api.services;

import bank_api.entity.Account;

import java.util.List;

public interface AccountService {
    public String checkBalance(int id);

    public void deposit(int id, String sum);

    public Account findById(int id);

    public List<Account> findByClientId(int clientId);

    public List<Account> findAll();
}
