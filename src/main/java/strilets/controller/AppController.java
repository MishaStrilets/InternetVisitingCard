/**
 * Class controller which manage incoming requests and redirect to proper response.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import strilets.model.Card;
import strilets.model.Review;
import strilets.model.User;
import strilets.model.Search;
import strilets.service.ReviewService;
import strilets.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	ReviewService reviewService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	final String anonym = "anonymousUser";

	/**
	 * Method return home page.
	 */
	@RequestMapping("/")
	String index() {
		return "index";
	}

	/**
	 * Method return a list of all cards.
	 */
	@RequestMapping(value = { "/list-cards" }, method = RequestMethod.GET)
	public String listCards(ModelMap model) {
		String role = new String();
		if (getPrincipal().equals(anonym))
			role = anonym;
		else
			role = userService.getUserByLogin(getPrincipal(), true).getRole();

		List<User> users = userService.getAllUsers(role);
		model.addAttribute("users", userService.sortUsersByRating(users));
		Search search = new Search();
		model.addAttribute("search", search);
		return "list_cards";
	}

	/**
	 * Method be called on form submission, handling POST request for searching
	 * cards in database.
	 */
	@RequestMapping(value = { "/list-cards" }, method = RequestMethod.POST)
	public String searchCards(Search search, ModelMap model) {
		String role = new String();
		if (getPrincipal().equals(anonym))
			role = anonym;
		else
			role = userService.getUserByLogin(getPrincipal(), true).getRole();

		List<User> users = userService.getUsers(search, role);
		model.addAttribute("users", userService.sortUsersByRating(users));
		model.addAttribute("search", search);
		return "list_cards";
	}

	/**
	 * Method provide to registered a new user.
	 */
	@RequestMapping(value = { "/registration-user" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	/**
	 * Method be called on form submission, handling POST request for saving
	 * user in database. It also validates the user input.
	 */
	@RequestMapping(value = { "/registration-user" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (!userService.isUserLoginUnique(user.getId(), user.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { user.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("login", user.getLogin());

		return "registration_success";
	}

	/**
	 * Method provide to update card.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "/edit-card-{login}" }, method = RequestMethod.GET)
	public String editCard(@PathVariable String login, ModelMap model) throws IOException {

		if (!getPrincipal().equals(login))
			return "access_denied";

		User user = userService.getUserByLogin(login, true);
		Card card = new Card(user);

		model.addAttribute("card", card);
		return "edit_card";
	}

	/**
	 * Method be called on form submission, handling POST request for updating
	 * card in database.
	 */
	@RequestMapping(value = { "/edit-card-{login}" }, method = RequestMethod.POST)
	public String updateCard(@Valid Card card, BindingResult result, ModelMap model, @PathVariable String login)
			throws IOException {

		if (result.hasErrors()) {
			return "edit_card";
		}

		User userOld = userService.getUserByLogin(login, true);
		User userNew = userService.copyCardToUser(userOld, card);
		userService.updateUser(userNew);

		return "edit_card";
	}

	/**
	 * Method provide to view visiting card.
	 */
	@RequestMapping(value = { "/{login}" }, method = RequestMethod.GET)
	public String viewCard(@PathVariable String login, ModelMap model) {
		User user = null;

		if (login.equals(getPrincipal()))
			user = userService.getUserByLogin(login, true);
		else
			user = userService.getUserByLogin(login, false);

		if (user == null) {
			model.addAttribute("login", login);
			return "no_user";
		} else {
			model.addAttribute("user", user);
			return "view_card";
		}
	}

	/**
	 * Method provide to view image.
	 */
	@RequestMapping(value = "/image-{login}")
	public void getImage(HttpServletResponse response, @PathVariable String login) throws IOException {
		response.setContentType(userService.getUserByLogin(login, true).getType());
		byte[] buffer = userService.getUserByLogin(login, true).getImage();
		InputStream in1 = new ByteArrayInputStream(buffer);
		IOUtils.copy(in1, response.getOutputStream());
	}

	/**
	 * Method delete an user.
	 */
	@RequestMapping(value = { "/delete-user-{login}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String login, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		userService.deleteUser(login);

		model.addAttribute("login", login);
		return "delete_user";
	}

	/**
	 * Method delete an user by admin.
	 */
	@RequestMapping(value = { "/admin-delete-user-{login}" }, method = RequestMethod.GET)
	public String deleteUserByAdmin(@PathVariable String login) {
		userService.deleteUser(login);
		return "redirect:/list-cards";
	}

	/**
	 * Method delete an image.
	 */
	@RequestMapping(value = { "/delete-image-{login}" }, method = RequestMethod.GET)
	public String deleteImage(@PathVariable String login) {
		if (!getPrincipal().equals(login))
			return "access_denied";

		User user = userService.getUserByLogin(login, true);
		user.setNameImage("");
		user.setType("");
		user.setImage(null);
		userService.updateUser(user);
		return "redirect:/edit-card-{login}";
	}

	/**
	 * Method handles access denied redirect.
	 */
	@RequestMapping(value = "/access_denied")
	public String accessDeniedPage() {
		return "access_denied";
	}

	/**
	 * Method handles login GET requests. If users is already logged and tries
	 * to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/";
		}
	}

	/**
	 * Method handles logout requests.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/";
	}

	/**
	 * Method provide to add review.
	 */
	@RequestMapping(value = { "/add-review-{login}" }, method = RequestMethod.GET)
	public String newReview(@PathVariable String login, ModelMap model) {
		User user = userService.getUserByLogin(login, false);

		if (user == null) {
			model.addAttribute("login", login);
			return "no_user";
		} else {
			Review review = new Review();
			model.addAttribute("review", review);
			return "add_review";
		}
	}

	/**
	 * Method be called on form submission, handling POST request for adding
	 * review in database.
	 */
	@RequestMapping(value = { "/add-review-{login}" }, method = RequestMethod.POST)
	public String saveReview(@Valid Review review, BindingResult result, ModelMap model, @PathVariable String login)
			throws IOException {
		if (result.hasErrors()) {
			return "add_review";
		}

		User user = userService.getUserByLogin(login, false);
		review.setUser(user);
		reviewService.saveReview(review);
		return "review_success";
	}

	/**
	 * Method return a list of all reviews.
	 */
	@RequestMapping(value = { "/list-reviews-{login}" }, method = RequestMethod.GET)
	public String listReviews(@PathVariable String login, ModelMap model) {

		User user = userService.getUserByLogin(login, false);

		if (user == null) {
			model.addAttribute("login", login);
			return "no_user";
		} else {
			model.addAttribute("averageRating", userService.countAverageRating(user));
			model.addAttribute("reviews", user.getReviews());
			return "list_reviews";
		}
	}

	/**
	 * Method return the principal of logged user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		return userName;
	}

	/**
	 * Method return true if users is already authenticated, else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}
}