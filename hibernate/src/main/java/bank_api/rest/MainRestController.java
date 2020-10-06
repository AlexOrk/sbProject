package bank_api.rest;

import java.util.List;

import bank_api.dao.AccountDAO;
import bank_api.dao.CardDAO;
import bank_api.dao.ClientDAO;
import bank_api.entity.Client;
import bank_api.services.AccountService;
import bank_api.services.CardService;
import bank_api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MainRestController {

	// For test
	private ClientDAO clientDAO;
	private AccountDAO accountDAO;
	private CardDAO cardDAO;

	// For release
	private ClientService clientService;
	private AccountService accountService;
	private CardService cardService;
	
	@Autowired
	public MainRestController(ClientDAO clientDAO, AccountService accountService, CardDAO cardDAO) {
		this.clientDAO = clientDAO;
		this.accountService = accountService;
		this.cardDAO = cardDAO;
	}
	@GetMapping("/check")
	public String check(HttpSession httpSession) {
		System.out.println(httpSession.getId());
		return httpSession.getId();
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/clients")
	public List<Client> findAll() {
		List<Client> clients = clientDAO.findAll();
//		for (Client u : clients)
//			System.out.println(u);
		return clients;
	}

	@GetMapping("/clients/{clientId}")
	public Client getClient(@PathVariable int clientId) {

		Client client = clientDAO.findById(clientId);
		System.out.println(client);

		if (client == null) {
			throw new RuntimeException("Client id not found - " + clientId);
		}

		return client;
	}

	
}










