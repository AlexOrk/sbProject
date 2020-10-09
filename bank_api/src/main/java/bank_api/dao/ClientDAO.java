package bank_api.dao;

import bank_api.entity.Client;

import java.util.List;

public interface ClientDAO {

	public List<Client> findAll();

	public Client findById(int id);

//	public void save(Client client);

//	public void deleteById(int id);
		
}
