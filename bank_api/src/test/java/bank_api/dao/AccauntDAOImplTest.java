package bank_api.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class AccauntDAOImplTest {

	@Autowired
	private AccountDAOImpl accountDAO;

	@Test
	public void testFindAll_test() {
		assertEquals(1, accountDAO.findAll().size());
	}

	@Test
	public void findById_test() {
		assertEquals(123, accountDAO.findById(1).getNumber());
	}
}







