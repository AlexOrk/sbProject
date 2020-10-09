package bank_api.dao;

import java.util.List;

import bank_api.entity.Account;

public interface AccountDAO {

	public List<Account> findAll();

	public Account findById(int id);

//	public List<Account>  findByCliendId(int id);

//	public void save(Account account);

//	public void deleteById(int id);

	public void merge(Account account);
}
