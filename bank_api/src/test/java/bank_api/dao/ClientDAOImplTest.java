package bank_api.dao;

import bank_api.entity.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ClientDAOImplTest {

	@Autowired
	private ClientDAOImpl clientDAO;

	@Test
	public void testFindAll_test() {
		assertEquals(1, clientDAO.findAll().size());
	}

	@Test
	public void findById_test() {
		assertEquals("Jake", clientDAO.findById(1).getName());
	}
}







