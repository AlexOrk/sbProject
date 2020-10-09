package bank_api.controllers;

import bank_api.entity.Account;
import bank_api.entity.Card;
import bank_api.services.AccountService;
import bank_api.services.CardService;
import bank_api.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/** ViewController receives data from the clients browser.
 Used to work with the web GUI. */

@Controller
@RequestMapping("/api")
public class ViewController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private ClientService clientService;
	private AccountService accountService;
	private CardService cardService;

	@Autowired
	public ViewController(ClientService clientService,
						  AccountService accountService,
						  CardService cardService) {
		this.clientService = clientService;
		this.accountService = accountService;
		this.cardService = cardService;
	}

	// Start work from here
	@GetMapping("/welcome")
	public String welcome() {
		logger.info("\"/welcome\"");
		logger.info("Return welcome page");
		return "welcome";
	}

	// The controller searches all accounts and return them to view or select
	@GetMapping("/selectAccount")
	public String selectAccount(@RequestParam("id") int clientId,
						@RequestParam("action") String action,
						Model model) {
		logger.info("\"/check\"");

		List<Account> accounts = clientService.findById(clientId).getAccounts();
		model.addAttribute("accounts", accounts);

		if (action.equals("check")) {
			logger.info("Return amount page");
			return "amount";
		} else if (action.equals("deposit")){
			logger.info("Return accounts page, action is deposit");
			model.addAttribute("flag", true);
			return "accounts";
		} else {
			logger.info("Return accounts page, action is orderCart");
			model.addAttribute("flag", false);
			return "accounts";
		}
	}

	// The controller searches an account and returns deposit form page
	@GetMapping("/deposit")
	public String deposit(@RequestParam("id") int accountId, Model model) {
		logger.info("\"/selectAccount\"");

		Account account = accountService.findById(accountId);
		model.addAttribute("account", account);

		logger.info("Return deposit page");
		return "deposit";
	}

	// The controller saves deposit on selected account
	@PostMapping("/saveDeposit")
	public String saveDeposit(@ModelAttribute("account") Account newAccount,
							  Model model) {
		logger.info("\"/saveDeposit\"");

		Account account = accountService.findById(newAccount.getId());
		newAccount.setAmount(account.getAmount().add(newAccount.getAmount()));
		newAccount.setClient(account.getClient());
		accountService.merge(newAccount);
		logger.info("Amount was deposited!");

		List<Account> accounts = newAccount.getClient().getAccounts();
		model.addAttribute("accounts", accounts);

		logger.info("Return amount page");
		return "amount";
	}

	// The controller searches all cards from all clients accounts and returns them
	@GetMapping("/viewCards")
	public String viewCards(@RequestParam("id") int clientId, Model model) {
		logger.info("\"/viewCards\"");

		List<Account> accounts = clientService.findById(clientId).getAccounts();
		List<Card> cards = accountService.getAllCards(accounts);

		model.addAttribute("accounts", accounts);
		model.addAttribute("cards", cards);

		logger.info("Return cards page");
		return "cards";
	}

	// The controller creates a new card for selected account and returns info about
	// all users cards
	@GetMapping("/createCard")
	public String createCard(@RequestParam("id") int accountId,
							 RedirectAttributes redirectAttributes) {
		logger.info("\"/createCard\"");

		Account selectedAccount = accountService.findById(accountId);
		Card newCard = cardService.createCard(selectedAccount);
		cardService.save(newCard);
		logger.info("Card was saved!");

		int clientId = selectedAccount.getClient().getId();
		redirectAttributes.addAttribute("id", clientId);

		logger.info("Redirect to /viewCards");
		return "redirect:/api/viewCards";
	}


}










