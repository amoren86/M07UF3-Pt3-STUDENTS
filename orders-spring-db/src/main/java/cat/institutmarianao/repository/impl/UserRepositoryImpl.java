package cat.institutmarianao.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.model.User;
import cat.institutmarianao.repository.UserRepository;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User get(String username) {
		// TODO get user by username
		return null;
	}

	@Override
	public void save(User user) {
		// TODO save user
	}
}
