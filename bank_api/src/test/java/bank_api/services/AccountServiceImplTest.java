package bank_api.services;

import bank_api.dao.AccountDAO;
import bank_api.entity.Account;
import bank_api.entity.Card;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountDAO accountDAO;

    @Test
    public void findById_test() {
        int id = anyInt();
        when(accountDAO.findById(id)).thenReturn(
                new Account(123L, new BigDecimal(345)));
        Account account = accountService.findById(id);
        assertEquals(123L, account.getNumber());
    }

    @Test
    public void checkBalance_test() {
        int id = anyInt();
        when(accountDAO.findById(id)).thenReturn(
                new Account(123L, new BigDecimal(345)));
        assertEquals("345", accountService.checkBalance(id));
    }

    @Test
    public void getAllCards_test() {
        Card card1 = new Card();
        Card card2 = new Card();
        List<Card> cards = Arrays.asList(card1, card2);
        Account account = new Account();
        account.setCards(cards);
        List<Account> accounts = Arrays.asList(account);

        assertEquals(cards, accountService.getAllCards(accounts));
    }

    @Test
    public void checkAmount_test() {
        BigDecimal bd = new BigDecimal(new Random().nextInt(100000) - 5000);
        assertEquals(bd.setScale(2, RoundingMode.DOWN).abs(),
                accountService.checkAmount(bd));
    }
}
