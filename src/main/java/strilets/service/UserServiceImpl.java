package strilets.service;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import strilets.dao.UserDao;
import strilets.model.User;
import strilets.model.Review;
import strilets.model.Search;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getUserById(Integer id) {
		return dao.getUserById(id);
	}

	public User getUserByLogin(String login) {
		User user = dao.getUserByLogin(login);
		return user;
	}

	public List<User> getUsers(Search search) {
		List<User> users = dao.getUsers(search);
		return users;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.saveUser(user);
	}

	public void updateUser(User user) {
		User entity = dao.getUserById(user.getId());
		if (entity != null) {
			entity.setLogin(user.getLogin());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setName(user.getName());
			entity.setDescription(user.getDescription());
			entity.setPeople(user.getPeople());
			entity.setAddress(user.getAddress());
			entity.setEmail(user.getEmail());
			entity.setPhone(user.getPhone());
			entity.setLinkedin(user.getLinkedin());
			entity.setFacebook(user.getFacebook());
			entity.setTwitter(user.getTwitter());
			entity.setInstagram(user.getInstagram());
			entity.setRole(user.getRole());
			entity.setImage(user.getImage());
			entity.setNameImage(user.getNameImage());
			entity.setType(user.getType());
			entity.setFontColor(user.getFontColor());
			entity.setBackgroundColor(user.getBackgroundColor());
		}
	}

	public void deleteUser(String login) {
		dao.deleteUser(login);
	}

	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

	public boolean isUserLoginUnique(Integer id, String login) {
		User user = getUserByLogin(login);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	public Double countAverageRating(User user) {
		Integer sumRating = 0;
		Double avearageRating = 0.0;
		Set<Review> reviews = user.getReviews();

		for (Review r : reviews)
			sumRating += r.getRating();

		if (reviews.size() != 0)
			avearageRating = (double) (sumRating / reviews.size());

		return avearageRating;
	}

	public Set<User> sortUsersByRating(List<User> users) {
		HashMap<User, Double> usersMap = new HashMap<User, Double>();

		for (User u : users)
			usersMap.put(u, countAverageRating(u));

		HashMap<User, Double> sortedUsersMap = usersMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		return sortedUsersMap.keySet();
	}
}
