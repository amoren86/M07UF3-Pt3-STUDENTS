package cat.institutmarianao.repository;

import java.util.List;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;

public interface OrderDao {
	Order get(Long reference);

	List<Order> getAll();

	List<Order> findByUser(User client);

	void save(Order order);
}
