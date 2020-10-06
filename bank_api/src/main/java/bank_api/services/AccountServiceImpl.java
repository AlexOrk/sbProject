package bank_api.services;

import bank_api.dao.AccountDAO;
import bank_api.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return account.getAmount().toString();
    }
}
