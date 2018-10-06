/**
 * Interface which contains abstract methods for business logic user.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import strilets.model.User;
import strilets.model.Card;
import strilets.model.Search;

public interface UserService {

	List<User> getUsers(Search search, String role);

	User getUserByLogin(String login, Boolean authenticationOrAuthorization);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUser(String login);

	List<User> getAllUsers(String role);

	boolean isUserLoginUnique(Integer id, String login);

	Double countAverageRating(User user);

	Set<User> sortUsersByRating(List<User> users);

	User copyCardToUser(User user, Card card) throws IOException;
}