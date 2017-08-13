package strilets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import strilets.dao.CardDao;
import strilets.model.Card;
import strilets.model.Search;

@Service("cardService")
@Transactional
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Card getCardById(Integer id) {
		return dao.getCardById(id);
	}

	public Card getCardByLogin(String login) {
		Card card = dao.getCardByLogin(login);
		return card;
	}

	public List<Card> getCards(Search search) {
		List<Card> cards = dao.getCards(search);
		return cards;
	}

	public void saveCard(Card card) {
		card.setPassword(passwordEncoder.encode(card.getPassword()));
		dao.saveCard(card);
	}

	public void updateCard(Card card) {
		Card entity = dao.getCardById(card.getId());
		if (entity != null) {
			entity.setLogin(card.getLogin());
			if (!card.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(card.getPassword()));
			}
			entity.setName(card.getName());
			entity.setDescription(card.getDescription());
			entity.setPeople(card.getPeople());
			entity.setAddress(card.getAddress());
			entity.setEmail(card.getEmail());
			entity.setPhone(card.getPhone());
			entity.setFacebook(card.getFacebook());
			entity.setTwitter(card.getTwitter());
			entity.setInstagram(card.getInstagram());
			entity.setRole(card.getRole());
		}
	}

	public void deleteCard(String login) {
		dao.deleteCard(login);
	}

	public List<Card> getAllCards() {
		return dao.getAllCards();
	}

	public boolean isCardLoginUnique(Integer id, String login) {
		Card card = getCardByLogin(login);
		return (card == null || ((id != null) && (card.getId() == id)));
	}

}
