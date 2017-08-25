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
import org.springframework.web.multipart.MultipartFile;

import strilets.model.Card;
import strilets.model.User;
import strilets.model.Search;
import strilets.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	/**
	 * This method will return home page.
	 */
	@RequestMapping("/")
	String index() {
		return "index";
	}

	/**
	 * This method will list all visiting cards.
	 */
	@RequestMapping(value = { "/list-cards" }, method = RequestMethod.GET)
	public String listCards(ModelMap model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		Search search = new Search();
		model.addAttribute("search", search);
		return "list_cards";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * searching cards in database.
	 */
	@RequestMapping(value = { "/list-cards" }, method = RequestMethod.POST)
	public String searchCards(Search search, ModelMap model) {
		List<User> users = userService.getUsers(search);
		model.addAttribute("users", users);
		model.addAttribute("search", search);
		return "list_cards";
	}

	/**
	 * This method will provide to registered a new user.
	 */
	@RequestMapping(value = { "/registration-user" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input.
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

		user.setRole("USER");
		user.setPeople("");
		user.setAddress("");
		user.setEmail("");
		user.setPhone("");
		user.setFacebook("");
		user.setTwitter("");
		user.setInstagram("");
		user.setNameImage("");
		user.setFontColor("#000000");
		user.setBackgroundColor("#ffffff");
		userService.saveUser(user);
		model.addAttribute("login", user.getLogin());
		return "registration_success";
	}

	/**
	 * This method will provide to update visiting card.
	 */
	@RequestMapping(value = { "/edit-card-{login}" }, method = RequestMethod.GET)
	public String editCard(@PathVariable String login, ModelMap model) {

		if (!getPrincipal().equals(login))
			return "access_denied";

		User user = userService.getUserByLogin(login);

		Card card = new Card();

		card.setLogin(user.getLogin());
		card.setName(user.getName());
		card.setDescription(user.getDescription());
		card.setPeople(user.getPeople());
		card.setAddress(user.getAddress());
		card.setEmail(user.getEmail());
		card.setPhone(user.getPhone());
		card.setFacebook(user.getFacebook());
		card.setTwitter(user.getTwitter());
		card.setInstagram(user.getInstagram());
		card.setNameImage(user.getNameImage());
		card.setFontColor(user.getFontColor());
		card.setBackgroundColor(user.getBackgroundColor());

		model.addAttribute("card", card);
		return "edit_card";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating card in database.
	 */
	@RequestMapping(value = { "/edit-card-{login}" }, method = RequestMethod.POST)
	public String updateCard(@Valid Card card, BindingResult result, ModelMap model, @PathVariable String login)
			throws IOException {

		if (result.hasErrors()) {
			return "edit_card";
		}

		User user = userService.getUserByLogin(login);
		saveCard(user, card);

		return "edit_card";
	}

	/**
	 * This method will provide to view visiting card.
	 */
	@RequestMapping(value = { "/{login}" }, method = RequestMethod.GET)
	public String viewCard(@PathVariable String login, ModelMap model) {
		User user = userService.getUserByLogin(login);
		if (user == null) {
			model.addAttribute("login", login);
			return "no_user";
		} else {
			model.addAttribute("user", user);
			return "view_card";
		}
	}

	/**
	 * This method will provide to view user image.
	 */
	@RequestMapping(value = "/image-{login}")
	public void getUserImage(HttpServletResponse response, @PathVariable String login) throws IOException {
		response.setContentType(userService.getUserByLogin(login).getType());
		byte[] buffer = userService.getUserByLogin(login).getImage();
		InputStream in1 = new ByteArrayInputStream(buffer);
		IOUtils.copy(in1, response.getOutputStream());
	}

	/**
	 * This method will delete an user by it's login.
	 */
	@RequestMapping(value = { "/delete-card-{login}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String login) {
		userService.deleteUser(login);
		return "redirect:/list-cards";
	}

	/**
	 * This method will delete an image by it's login.
	 */
	@RequestMapping(value = { "/delete-image-{login}" }, method = RequestMethod.GET)
	public String deleteUmage(@PathVariable String login) {
		if (!getPrincipal().equals(login))
			return "access_denied";

		User user = userService.getUserByLogin(login);
		user.setNameImage("");
		user.setType("");
		user.setImage(null);
		userService.updateUser(user);
		return "redirect:/edit-card-{login}";
	}

	/**
	 * This method handles access denied redirect.
	 */
	@RequestMapping(value = "/access_denied")
	public String accessDeniedPage() {
		return "access_denied";
	}

	/**
	 * This method handles login GET requests. If users is already logged and
	 * tries to goto login page again, will be redirected to list page.
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
	 * This method handles logout requests. Toggle the handlers if you are
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
	 * This method returns the principal of logged user.
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
	 * This method returns true if users is already authenticated, else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	/**
	 * This method copy data from Card to User.
	 */
	private void saveCard(User user, Card card) throws IOException {
		user.setLogin(card.getLogin());
		user.setName(card.getName());
		user.setDescription(card.getDescription());
		user.setPeople(card.getPeople());
		user.setAddress(card.getAddress());
		user.setEmail(card.getEmail());
		user.setPhone(card.getPhone());
		user.setFacebook(card.getFacebook());
		user.setTwitter(card.getTwitter());
		user.setInstagram(card.getInstagram());
		user.setNameImage(card.getNameImage());
		user.setFontColor(card.getFontColor());
		user.setBackgroundColor(card.getBackgroundColor());

		if ((!("".equals(card.getFile().getOriginalFilename())))) {
			MultipartFile multipartFile = card.getFile();
			user.setNameImage(multipartFile.getOriginalFilename());
			user.setType(multipartFile.getContentType());
			user.setImage(multipartFile.getBytes());
		}

		userService.updateUser(user);
	}

}