package strilets.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import strilets.model.Card;
import strilets.model.Search;
import strilets.service.CardService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	CardService cardService;

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
		List<Card> cards = cardService.getAllCards();
		model.addAttribute("cards", cards);
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
		List<Card> cards = cardService.getCards(search);
		model.addAttribute("cards", cards);
		model.addAttribute("search", search);
		return "list_cards";
	}

	/**
	 * This method will provide to registered a new user.
	 */
	@RequestMapping(value = { "/registration-user" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		Card card = new Card();
		model.addAttribute("card", card);
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input.
	 */
	@RequestMapping(value = { "/registration-user" }, method = RequestMethod.POST)
	public String saveUser(@Valid Card card, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (!cardService.isCardLoginUnique(card.getId(), card.getLogin())) {
			FieldError loginError = new FieldError("card", "login", messageSource.getMessage("non.unique.login",
					new String[] { card.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "registration";
		}

		card.setRole("USER");
		cardService.saveCard(card);
		model.addAttribute("success", card.getLogin());
		return "registration_success";
	}

	/**
	 * This method will provide to update visiting card.
	 */
	@RequestMapping(value = { "/edit-card-{login}" }, method = RequestMethod.GET)
	public String editCard(@PathVariable String login, ModelMap model) {

		if (!getPrincipal().equals(login))
			return "access_denied";

		Card card = cardService.getCardByLogin(login);
		model.addAttribute("card", card);
		return "edit_card";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating card in database.
	 */
	@RequestMapping(value = { "/edit-card-{login}" }, method = RequestMethod.POST)
	public String updateCard(@Valid Card card, BindingResult result, ModelMap model, @PathVariable String login) {

		if (result.hasErrors()) {
			return "edit_card";
		}
		cardService.updateCard(card);
		return "view_card";
	}

	/**
	 * This method will provide to view visiting card.
	 */
	@RequestMapping(value = { "/{login}" }, method = RequestMethod.GET)
	public String viewCard(@PathVariable String login, ModelMap model) {
		Card card = cardService.getCardByLogin(login);
		model.addAttribute("card", card);
		return "view_card";
	}

	/**
	 * This method will delete an user by it's login.
	 */
	@RequestMapping(value = { "/delete-card-{login}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String login) {
		cardService.deleteCard(login);
		return "redirect:/list-cards";
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

}