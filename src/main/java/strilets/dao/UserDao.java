package strilets.dao;

import java.util.List;

import strilets.model.User;
import strilets.model.Search;

public interface UserDao {

	User getUserById(Integer id);

	List<User> getUsers(Search search);

	User getUserByLogin(String login);

	void saveUser(User user);

	void deleteUser(String login);

	List<User> getAllUsers();
}