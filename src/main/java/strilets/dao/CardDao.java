package strilets.dao;

import java.util.List;

import strilets.model.Card;
import strilets.model.Search;

public interface CardDao {

	Card getCardById(Integer id);

	List<Card> getCards(Search search);

	Card getCardByLogin(String login);

	void saveCard(Card card);

	void deleteCard(String login);

	List<Card> getAllCards();

}