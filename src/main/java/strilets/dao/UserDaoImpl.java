package strilets.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import strilets.model.User;
import strilets.model.Search;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public User getUserById(Integer id) {
		User user = getByKey(id);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers(Search search) {
		logger.info("Search user: {}", search);
		Criteria criteria = createEntityCriteria();
		List<User> users = criteria.add(Restrictions.like("name", search.getName(), MatchMode.ANYWHERE))
				.add(Restrictions.like("description", search.getDescription(), MatchMode.ANYWHERE))
				.add(Restrictions.like("address", search.getAddress(), MatchMode.ANYWHERE)).list();
		return users;
	}

	public User getUserByLogin(String login) {
		logger.info("Get user login : {}", login);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		User user = (User) criteria.uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = criteria.list();
		return users;
	}

	public void saveUser(User user) {
		logger.info("Save user: {}", user);
		persist(user);
	}

	public void deleteUser(String login) {
		logger.info("Delete user login: {}", login);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		User user = (User) criteria.uniqueResult();
		delete(user);
	}

}
