//package bank_api.controllers;
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import bank_api.dao.AccountDAO;
//import bank_api.dao.CardDAO;
//import bank_api.dao.ClientDAO;
//import bank_api.entity.Account;
//import bank_api.entity.Card;
//import bank_api.entity.Client;
//import bank_api.services.AccountService;
//import bank_api.services.CardService;
//import bank_api.services.ClientService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class RestMainController {
//    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
//
//    // For test
//    private ClientDAO clientDAO;
//    private AccountDAO accountDAO;
//    private CardDAO cardDAO;
//
//    // For release
//    private ClientService clientService;
//    private AccountService accountService;
//    private CardService cardService;
//
//    @Autowired
//    public RestMainController(ClientDAO clientDAO, AccountService accountService, CardService cardService) {
//        this.clientDAO = clientDAO;
//        this.accountService = accountService;
//        this.cardService = cardService;
//    }
//
//    @GetMapping("/check")
//    public String check(@RequestParam("id") int clientId) {
//        logger.info("\"/check\"");
//        String amount = accountService.checkBalance(clientId);
//        logger.info("Return amount: " + amount);
//        return amount;
//    }
//
//    @GetMapping("/deposit")
//    public String deposit(@RequestParam("id") int clientId,
//                          @RequestParam("sum") String sum) {
//        logger.info("\"/deposit\"");
//        accountService.deposit(clientId, sum);
//        logger.info("Amount was deposited!");
//        return null;
//    }
//
////    @GetMapping("/clients")
////    public List<Client> findAll() {
////        List<Client> clients = clientDAO.findAll();
////        for (Client c : clients)
////            logger.info("Found clients: " + c);
////        return clients;
////    }
////
////    @GetMapping("/clients/{clientId}")
////    public Client getClient(@PathVariable int clientId) {
////
////        Client client = clientDAO.findById(clientId);
////        logger.info("Found client: " + client);
////
////        if (client == null) {
////            throw new RuntimeException("Client id not found - " + clientId);
////        }
////
////        return client;
////    }
//
//    @PostMapping("/new_card")
//    public String addCard(@RequestBody String jsonString) {
//
//        JSONObject root = new JSONObject(jsonString);
//
//        int account_id = (int) root.get("id");
//        long number = cardService.getNewCardNumber();
//        int cvv = cardService.generateCvv();
//        String date = cardService.getExpirationDate();
//        Account selectedAccount = accountService.findById(account_id);
//
//        Card newCard = new Card(number, date, cvv, selectedAccount);
//        cardService.save(newCard);
//
//        return newCard.toString();
//    }
//
//    @PostMapping("/view_cards")
//    public String viewCards(@RequestBody String jsonString) throws IOException {
//
//        ObjectMapper mapper = new ObjectMapper();
//        StringReader reader = new StringReader(jsonString);
//
//        Client client = mapper.readValue(reader, Client.class);
//
//        List<Account> accounts = accountService.findByClientId(client.getId());
//        List<Card> cards = new ArrayList<>();
//        for (Account account : accounts) {
//            cards.addAll(cardService.findByAccountId(account.getId()));
//        }
//        return cards.toString();
//    }
//
//    @PostMapping("/view_balance")
//    public String viewBalance(@RequestBody Account account) {
//        Account selectedAccount = accountService.findById(account.getId());
//        return selectedAccount.toString();
//    }
//
//    @PostMapping("/deposit")
//    public String deposit(@RequestBody String jsonString) throws IOException {
//        JSONObject root = new JSONObject(jsonString);
//
//        int account_id = (int) root.get("account_id");
//        BigDecimal amount = new BigDecimal (root.get("amount").toString());
//
//        Account account = accountService.findById(account_id);
//        account.setAmount(account.getAmount().add(amount));
//
//        accountService.merge(account);
//
//        return account.toString();
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
