package bank_api.services;

import bank_api.dao.ClientDAO;
import bank_api.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientDAO clientDAO;

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Client findById(int id) {
        return clientDAO.findById(id);
    }

    @Override
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Override
    public void deleteById(int id) {
        clientDAO.deleteById(id);
    }
}
