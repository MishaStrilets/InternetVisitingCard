package strilets.service;

import java.util.List;

import strilets.model.Card;
import strilets.model.Search;

public interface CardService {

	Card getCardById(Integer id);

	List<Card> getCards(Search search);

	Card getCardByLogin(String login);

	void saveCard(Card card);

	void updateCard(Card card);

	void deleteCard(String login);

	List<Card> getAllCards();

	boolean isCardLoginUnique(Integer id, String login);

}