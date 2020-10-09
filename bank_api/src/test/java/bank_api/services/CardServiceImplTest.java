package bank_api.services;

import bank_api.dao.CardDAO;
import bank_api.entity.Account;
import bank_api.entity.Card;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardDAO cardDAO;

    @Test
    public void findById_test() {
        int id = anyInt();
        when(cardDAO.findById(id)).thenReturn(
                new Card(4276550100000004L, "10/23", 10, new Account()));
        Card card = cardService.findById(id);
        assertEquals(10, card.getCvv());
    }

    @Test
    public void getExpirationDate_test() {
        assertEquals("10/23", cardService.getExpirationDate());
    }

    @Test
    public void getNewCardNumber_test() {
        when(cardDAO.findAll()).thenReturn(
                Arrays.asList(
                        new Card(4276550100000004L, "10/23", 1, new Account()))
        );
        List<Card> cards = cardService.findAll();
        long num = cardService.getNewCardNumber();

        if (cards.size() == 0)
            assertEquals(4276550100000001L, num);
        else
            assertEquals(4276550100000005L, num);

    }

    @Test
    public void createCard_test() {
        Account account = new Account();
        assertEquals(account, cardService.createCard(account).getAccount());
    }
}
