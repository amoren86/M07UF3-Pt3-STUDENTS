package cat.institutmarianao.repository.impl;

import java.util.List;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderDao;

//TODO Convert it in a transactional repository
public class OrderDaoImpl implements OrderDao {
	@Override
	public Order get(Long reference) {
		// TODO get order by reference
		return null;
	}

	@Override
	public List<Order> getAll() {
		// TODO get all orders
		return null;
	}

	@Override
	public List<Order> findByUser(User client) {
		// TODO find order by client
		return null;
	}

	@Override
	public void save(Order order) {
		// TODO save order
	}

	@Override
	public Order update(Order order) {
		// TODO update order
		return null;
	}
}
