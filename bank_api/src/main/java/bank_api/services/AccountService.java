package bank_api.services;

public interface AccountService {
    public String checkBalance(int id);

    public void deposit(int id, String sum);
}
