package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import cat.institutmarianao.RepositoryTestContext;
import cat.institutmarianao.model.Order;
import cat.institutmarianao.model.Order.Status;
import cat.institutmarianao.model.User;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = { "classpath:/repository-test-context.xml" })
@ContextConfiguration(classes = { RepositoryTestContext.class })
class OrderRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Test
	@Transactional
	void saveAndGetShouldSaveAndThenGet() {
		/* Setup an order */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = Mock.createUser(username);
		userRepository.save(user);

		Order order = Mock.createOrder(user);

		/* Assert order does not exists in the database */
		assertNull(order.getReference());

		/* Test save order */
		orderRepository.save(order);

		/* Test get order by reference */
		Order orderFromDb = orderRepository.get(order.getReference());

		/* Verification */
		/* Assert order exists in the database */
		assertNotNull(orderFromDb);

		/* Assert order in the database is equals to saved one */
		assertEquals(order, orderFromDb);
	}

	@Test
	@Transactional
	void findAllShouldReturnAllItems() {
		/* Setup some orders */
		int length = 5;
		List<Order> orders = new ArrayList<>();

		for (int i = 0; i < length; i++) {
			/* User must exist previously in the database */
			User user = Mock.createUser(Mock.createRandomString(User.MAX_USERNAME));
			userRepository.save(user);

			/* Order does not exists in database */
			Order order = Mock.createOrder(user);
			orders.add(order);
			assertNull(order.getReference());
		}

		/* Test get all orders should return nothing */
		assertEquals(0, orderRepository.getAll().size());

		/* Save all orders in the database */
		for (Order order : orders) {
			orderRepository.save(order);
		}

		/* Test get all orders */
		List<Order> ordersFromDb = orderRepository.getAll();

		/* Verification */
		/* Orders are all in the database */
		assertEquals(orders.size(), ordersFromDb.size());

		/* Orders in the database are the stored ones */
		assertTrue(ordersFromDb.containsAll(orders));
	}

	@Test
	@Transactional
	void findByUsernameShouldReturnAllUsernameItems() {
		/* Setup some orders from different users */
		int length = 10;
		List<Order> userOrders = new ArrayList<>();
		List<Order> anotherUserOrders = new ArrayList<>();

		User user = Mock.createUser(Mock.createRandomString(User.MAX_USERNAME));
		User anotherUser = Mock.createUser(Mock.createRandomString(User.MAX_USERNAME));

		/* Users must exist previously in the database */
		userRepository.save(user);
		userRepository.save(anotherUser);

		for (int i = 0; i < length; i++) {
			Order order;
			if (i % 2 == 0) {
				/* One order for the user */
				order = Mock.createOrder(user);
				userOrders.add(order);
			} else {
				/* Another order for the other user */
				order = Mock.createOrder(anotherUser);
				anotherUserOrders.add(order);
			}
			/* No order exists in database */
			assertNull(order.getReference());
		}

		/* Test method - database has no orders */
		assertEquals(0, orderRepository.getAll().size());

		/* Save orders */
		for (Order order : userOrders) {
			orderRepository.save(order);
		}
		for (Order order : anotherUserOrders) {
			orderRepository.save(order);
		}

		/* Test find orders by user */
		List<Order> ordersFromDb = orderRepository.findByUser(user);

		/* Verification */
		/* Orders in the database are equal to the stored ones */
		assertEquals(userOrders.size(), ordersFromDb.size());
		assertTrue(ordersFromDb.containsAll(userOrders));
	}

	@Test
	@Transactional
	void updateShouldSaveChanges() {
		/* Setup an order */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = Mock.createUser(username);
		userRepository.save(user);

		Order order = Mock.createOrder(user);

		/* Save the order */
		orderRepository.save(order);
		Order orderFromDb = orderRepository.get(order.getReference());

		/* Order exists in the database */
		assertNotNull(orderFromDb);
		assertNotNull(orderFromDb.getReference());

		/* Order does not have the new changes */
		User anotherClient = Mock.createUser("changed");
		Order.Status anotherStatus = getAnotherStatus(orderFromDb.getStatus());
		Date anotherDate = new Date();

		assertNotEquals(anotherClient, orderFromDb.getClient());
		assertNotEquals(anotherDate, orderFromDb.getStartDate());
		assertNotEquals(anotherDate, orderFromDb.getDeliveryDate());
		assertNotEquals(anotherStatus, orderFromDb.getStatus());

		/* Do changes on the order */
		orderFromDb.setClient(anotherClient);
		orderFromDb.setDeliveryDate(anotherDate);
		orderFromDb.setStartDate(anotherDate);
		orderFromDb.setStatus(anotherStatus);

		/* Test update the order */
		orderFromDb = orderRepository.update(order);

		/* Verification */
		/* Order exists in the database */
		assertNotNull(orderFromDb);
		assertNotNull(order.getReference());
		/* Order in the database is equal to the stored one */
		assertEquals(order, orderFromDb);

		/* Order has the expected changes */
		assertEquals(anotherClient, orderFromDb.getClient());
		assertEquals(anotherDate, orderFromDb.getStartDate());
		assertEquals(anotherDate, orderFromDb.getDeliveryDate());
		assertEquals(anotherStatus, orderFromDb.getStatus());
	}

	private Status getAnotherStatus(Status status) {
		return Status.values()[(status.ordinal() + 1) % Status.values().length];
	}
}
