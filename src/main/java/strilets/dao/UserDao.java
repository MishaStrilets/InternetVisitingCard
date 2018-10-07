/**
 * Interface which contains abstract methods for get, save and delete user.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.dao;

import java.util.List;

import strilets.model.User;
import strilets.model.Search;

public interface UserDao {

	List<User> getUsers(Search search, String role);

	User getUserByLogin(String login, Boolean visible);

	void saveUser(User user);

	void deleteUser(String login);

	List<User> getAllUsers(String role);
}