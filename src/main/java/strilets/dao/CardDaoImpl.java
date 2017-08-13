package strilets.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import strilets.model.Card;
import strilets.model.Search;

@Repository("cardDao")
public class CardDaoImpl extends AbstractDao<Integer, Card> implements CardDao {

	static final Logger logger = LoggerFactory.getLogger(CardDaoImpl.class);

	public Card getCardById(Integer id) {
		Card card = getByKey(id);
		return card;
	}

	@SuppressWarnings("unchecked")
	public List<Card> getCards(Search search) {
		logger.info("Search : {}", search);
		Criteria criteria = createEntityCriteria();
		List<Card> cards = criteria.add(Restrictions.like("name", search.getName(), MatchMode.ANYWHERE))
				.add(Restrictions.like("description", search.getDescription(), MatchMode.ANYWHERE))
				.add(Restrictions.like("address", search.getAddress(), MatchMode.ANYWHERE)).list();
		return cards;
	}

	public Card getCardByLogin(String login) {
		logger.info("Login : {}", login);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		Card card = (Card) criteria.uniqueResult();
		return card;
	}

	@SuppressWarnings("unchecked")
	public List<Card> getAllCards() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Card> cards = criteria.list();
		return cards;
	}

	public void saveCard(Card card) {
		persist(card);
	}

	public void deleteCard(String login) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		Card card = (Card) criteria.uniqueResult();
		delete(card);
	}

}
