package strilets.service;

import java.util.List;

import strilets.model.Card;

public interface CardService {

	Card getCardById(Integer id);

	List<Card> getCardsByName(String name);

	void saveCard(Card card);

	void updateCard(Card card);

	void deleteCard(Integer id);

	List<Card> getAllCards();

}