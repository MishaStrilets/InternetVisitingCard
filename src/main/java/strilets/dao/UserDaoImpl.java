/**
 * Class DAO which implements methods for get, save and delete user.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import strilets.model.User;
import strilets.model.Search;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<User> getUsers(Search search) {
		logger.info("Search users by criterias search: {}", search);
		Criteria criteria = createEntityCriteria();
		List<User> users = criteria.add(Restrictions.like("name", search.getName(), MatchMode.ANYWHERE))
				.add(Restrictions.like("description", search.getDescription(), MatchMode.ANYWHERE))
				.add(Restrictions.like("address", search.getAddress(), MatchMode.ANYWHERE)).list();
		return users;
	}

	public User getUserByLogin(String login) {
		logger.info("Get user: {}", login);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		User user = (User) criteria.uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		logger.info("Get all users.");
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = criteria.list();
		return users;
	}

	public void saveUser(User user) {
		logger.info("Save user: {}", user.getLogin());
		persist(user);
	}

	public void deleteUser(String login) {
		logger.info("Delete user: {}", login);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		User user = (User) criteria.uniqueResult();
		delete(user);
	}
}