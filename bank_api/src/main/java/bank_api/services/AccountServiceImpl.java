package bank_api.services;

import bank_api.dao.AccountDAO;
import bank_api.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public String checkBalance(int id) {
        Account account = accountDAO.findById(id);
        System.out.println(account.getAmount());
        return account.getAmount().toString();
    }

    @Override
    public void deposit(int id, String sum) {
        Account account = accountDAO.findById(id);
        BigDecimal bd = account.getAmount().add(new BigDecimal(sum));
        account.setAmount(bd);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(bd);
        accountDAO.save(account);

        Account account1 = accountDAO.findById(id);
        System.out.println(account1.getAmount());

        String account2 = checkBalance(1);
        System.out.println(account2);
        System.out.println();
        System.out.println();
        System.out.println();
    }

    @Override
    public Account findById(int id) {
        return accountDAO.findById(id);
    }

    @Override
    public List<Account> findByClientId(int clientId) {
        return accountDAO.findByCliendId(clientId);
    }

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }
}
