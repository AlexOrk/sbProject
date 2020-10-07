package bank_api.rest;

import bank_api.dao.AccountDAO;
import bank_api.dao.CardDAO;
import bank_api.dao.ClientDAO;
import bank_api.entity.Client;
import bank_api.services.AccountService;
import bank_api.services.CardService;
import bank_api.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ViewController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	// For test
	private ClientDAO clientDAO;
	private AccountDAO accountDAO;
	private CardDAO cardDAO;

	// For release
	private ClientService clientService;
	private AccountService accountService;
	private CardService cardService;

	@Autowired
	public ViewController(ClientDAO clientDAO, AccountService accountService, CardDAO cardDAO) {
		this.clientDAO = clientDAO;
		this.accountService = accountService;
		this.cardDAO = cardDAO;
	}

	@GetMapping("/start")
	public String start() {
		logger.info("\"/start\"");
		logger.info("Return welcome page");
		return "welcome";
	}

	@GetMapping("/check")
	public String check(@RequestParam("id") int clientId) {
		logger.info("\"/check\"");
		String amount = accountService.checkBalance(clientId);
		logger.info("Return amount: " + amount);
		return amount;
	}

	@GetMapping("/deposit")
	public String deposit(@RequestParam("id") int clientId,
						  @RequestParam("sum") String sum) {
		logger.info("\"/deposit\"");
		accountService.deposit(clientId, sum);
		logger.info("Amount was deposited!");
		return null;
	}
	
	@GetMapping("/clients")
	public List<Client> findAll() {
		List<Client> clients = clientDAO.findAll();
		for (Client c : clients)
			logger.info("Found clients: " + c);
		return clients;
	}

	@GetMapping("/clients/{clientId}")
	public Client getClient(@PathVariable int clientId) {

		Client client = clientDAO.findById(clientId);
		logger.info("Found client: " + client);

		if (client == null) {
			throw new RuntimeException("Client id not found - " + clientId);
		}

		return client;
	}

	
}










