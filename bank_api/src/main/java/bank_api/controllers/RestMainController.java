package bank_api.controllers;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import bank_api.entity.Account;
import bank_api.entity.Card;
import bank_api.entity.Client;
import bank_api.services.AccountService;
import bank_api.services.CardService;
import bank_api.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** RestMainController receives data from the clients browser.
 Used to demonstrate working with JSON files. */
@RestController
public class RestMainController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private ClientService clientService;
    private AccountService accountService;
    private CardService cardService;

    @Autowired
    public RestMainController(ClientService clientService, AccountService accountService, CardService cardService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.cardService = cardService;
    }


    @PostMapping("/new-card")
    public String addCard(@RequestBody String jsonString) {
        logger.info("\"/new-card\"");
        System.out.println(jsonString);
        JSONObject root = new JSONObject(jsonString);

        int account_id = (int) root.get("id");
        Account selectedAccount = accountService.findById(account_id);

        Card newCard = cardService.createCard(selectedAccount);
        cardService.save(newCard);
        logger.info("Card was added");

        return newCard.toString();
    }

    @PostMapping("/view-cards")
    public String viewCards(@RequestBody String jsonString) throws IOException {
        logger.info("\"/view-cards\"");
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonString);

        Client client = mapper.readValue(reader, Client.class);


        List<Account> accounts = client.getAccounts();
        System.out.println(accounts);
        List<Card> cards = new ArrayList<>();
        for (Account account : accounts) {
            cards.addAll(account.getCards());
        }
        return cards.toString();
    }

    @PostMapping("/view-balance")
    public String viewBalance(@RequestBody String jsonString) throws IOException {

        logger.info("\"/view-balance\"");

        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonString);

        Account account = mapper.readValue(reader, Account.class);
        return account.toString();
    }

    @PostMapping("/deposit")
    public String deposit(@RequestBody String jsonString) throws IOException {
        logger.info("\"/deposit\"");

        JSONObject root = new JSONObject(jsonString);
        int account_id = (int) root.get("account_id");
        BigDecimal amount = new BigDecimal (root.get("amount").toString());

        Account account = accountService.findById(account_id);
        account.setAmount(account.getAmount().add(amount));

        accountService.merge(account);
        logger.info("\"Amount was deposited\"");

        return account.toString();
    }


}










