package strilets.dao;

import java.util.List;

import strilets.model.Card;

public interface CardDao {

	Card getCardById(Integer id);

	List<Card> getCardsByName(String name);

	Card getCardByLogin(String login);

	void saveCard(Card card);

	void deleteCard(Integer id);

	List<Card> getAllCards();

}