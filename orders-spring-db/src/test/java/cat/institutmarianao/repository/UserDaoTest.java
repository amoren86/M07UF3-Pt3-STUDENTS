package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/dao-test-context.xml" })
class UserDaoTest {
	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	void saveAndGetShouldSaveAndThenGet() {
		/* Setup a user */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = Mock.createUser(username);

		/* Assert item does not exists in the database */
		User userFromDb = userDao.get(username);
		assertNull(userFromDb);

		/* Test methods */
		userDao.save(user);
		userFromDb = userDao.get(username);

		/* Verification - user exists and has the same values */
		assertNotNull(userFromDb);
		assertEquals(user, userFromDb);
	}
}
