package bank_api.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class CardDAOImplTest {

	@Autowired
	private CardDAOImpl cardDAO;

	@Test
	public void testFindAll_test() {
		assertEquals(0, cardDAO.findAll().size());
	}

	@Test
	public void findById_test() {
		assertNull(cardDAO.findById(1));
	}
}







