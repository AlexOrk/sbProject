package bank_api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import bank_api.controllers.RestMainController;

import bank_api.entity.Account;
import bank_api.entity.Card;
import bank_api.entity.Client;
import bank_api.services.AccountService;
import bank_api.services.CardService;
import bank_api.services.ClientService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(RestMainController.class)
public class RestMainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;
    @MockBean
    private ClientService clientService;
    @MockBean
    private CardService cardService;


    private Client client;
    private Account account;
    private Card card;

    @Before
    public void setUp() throws Exception {
        client = new Client();
        account = new Account();
        client.setId(1);
        client.setName("James");

        account.setNumber(1234);
        account.setId(1);
        account.setAmount(new BigDecimal("0.00"));
        account.setId(1);
        account.setClient(client);
        List<Account> accs = new ArrayList<>();
        accs.add(account);
        client.setAccounts(accs);
        card = new Card(4276550101010101L, "\"12/23\"", 123, account);
        card.setId(1);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        account.setCards(cards);
        card.setAccount(account);
    }

    @Test
    public void testAddCard() throws Exception {

        Mockito.when(accountService.findById(account.getId())).thenReturn(account);
        Mockito.when(cardService.createCard((account))).thenReturn(card);
        System.out.println(jsonPath("$.value", Matchers.is(card)));
        mockMvc.perform(MockMvcRequestBuilders.post("/new-card")
                .content(card.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", Matchers.is(card.getId())))
                .andExpect(jsonPath("$.number", Matchers.is(card.getNumber())))
                .andExpect(jsonPath("$.expDate", Matchers.is("12/23")))
                .andExpect(jsonPath("$.cvv", Matchers.is(card.getCvv())));

    }

    @Test
    public void testViewCards() throws Exception {

        System.out.println(account.getCards());
        mockMvc.perform(MockMvcRequestBuilders.post("/view-cards")
                .content(client.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", Matchers.is(card.getId())))
                .andExpect(jsonPath("$[0].number", Matchers.is(card.getNumber())))
                .andExpect(jsonPath("$[0].expDate", Matchers.is("12/23")))
                .andExpect(jsonPath("$[0].cvv", Matchers.is(card.getCvv())));
    }

    @Test
    public void testViewBalance() throws Exception {

        System.out.println(account);
        Mockito.when(accountService.findById(account.getId())).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.post("/view-balance")
                .content(String.valueOf(account))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", Matchers.is(account.getId())))
                .andExpect(jsonPath("$.number", Matchers.is(1234)))
                .andExpect(jsonPath("$.amount", Matchers.is(0.0)));
    }

    @Test
    public void testDeposit() throws Exception {

        System.out.println(account);
        String jsonString = "{\n" +
                "\"account_id\": 1,\n" +
                "\"number\": 123,\n" +
                "\"amount\":3.00,\n" +
                "\"client_id\": 1\n" +
                "}";
        Mockito.when(accountService.findById(account.getId())).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.post("/deposit")
                .content(String.valueOf(jsonString))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.number", Matchers.is(1234)))
                .andExpect(jsonPath("$.amount", Matchers.is(3.0)));
    }


}