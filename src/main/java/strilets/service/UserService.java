package strilets.service;

import java.util.List;

import strilets.model.User;
import strilets.model.Search;

public interface UserService {

	User getUserById(Integer id);

	List<User> getUsers(Search search);

	User getUserByLogin(String login);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUser(String login);

	List<User> getAllUsers();

	boolean isUserLoginUnique(Integer id, String login);

}