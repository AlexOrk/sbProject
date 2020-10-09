package bank_api.services;

import bank_api.entity.Client;

public interface ClientService {
    public Client findById(int id);
}
