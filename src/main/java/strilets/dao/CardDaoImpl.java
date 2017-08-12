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

@Repository("cardDao")
public class CardDaoImpl extends AbstractDao<Integer, Card> implements CardDao {

	static final Logger logger = LoggerFactory.getLogger(CardDaoImpl.class);

	public Card getCardById(Integer id) {
		Card card = getByKey(id);
		return card;
	}

	@SuppressWarnings("unchecked")
	public List<Card> getCardsByName(String name) {
		logger.info("Name : {}", name);
		Criteria criteria = createEntityCriteria();
		List<Card> cards = criteria.add(Restrictions.like("name", name, MatchMode.START)).list();
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

	public void deleteCard(Integer id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		Card card = (Card) criteria.uniqueResult();
		delete(card);
	}

}
