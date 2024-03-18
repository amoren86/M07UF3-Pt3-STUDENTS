package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/dao-test-context.xml" })
class ItemDaoTest {
	@Autowired
	private ItemDao itemDao;

	@Test
	@Transactional
	void saveAndGetShouldSaveAndThenGet() {
		/* Setup item */
		Item item = Mock.createItem();

		/* Assert item does not exists in the database */
		assertNull(item.getReference());
		assertEquals(0, itemDao.getAll().size());

		/* Test save item */
		itemDao.save(item);

		/* Test get item by reference */
		Item itemFromDb = itemDao.get(item.getReference());

		/* Verification */
		/* Assert item exists in the database */
		assertNotNull(itemFromDb);

		/* Assert item in the database is equals to saved one */
		assertEquals(item, itemFromDb);
	}

	@Test
	@Transactional
	void findAllShouldReturnAllItems() {
		/* Setup some items */
		List<Item> items = Arrays.asList(Mock.createItem(), Mock.createItem(), Mock.createItem(), Mock.createItem());

		/* Items does not exists in database */
		for (Item item : items) {
			assertNull(item.getReference());
		}

		/* Test get all items should return nothing */
		assertEquals(0, itemDao.getAll().size());

		/* Save all items in the database */
		for (Item item : items) {
			itemDao.save(item);
		}

		/* Test get all items */
		List<Item> itemsFromDb = itemDao.getAll();

		/* Verification */
		/* Items are all in the database */
		assertEquals(items.size(), itemDao.getAll().size());

		/* Items in the database are the stored ones */
		assertTrue(items.containsAll(itemsFromDb));
	}
}
