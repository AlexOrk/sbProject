package bank_api.services;

import bank_api.dao.ClientDAO;
import bank_api.entity.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientDAO clientDAO;

    @Test
    public void findById_test() {
        int id = anyInt();
        when(clientDAO.findById(id)).thenReturn(new Client("Jake"));
        Client client = clientService.findById(id);
        assertEquals("Jake", client.getName());
    }
}
