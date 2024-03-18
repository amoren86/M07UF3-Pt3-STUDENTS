package cat.institutmarianao.service;

import java.util.List;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import jakarta.validation.constraints.NotNull;

public interface OrderService {
	List<Order> getAll();

	Order get(@NotNull Long reference);

	List<Order> findByUser(@NotNull User client);

	void save(@NotNull Order order);

	Order update(Order order);
}
