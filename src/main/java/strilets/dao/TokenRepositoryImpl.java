/**
 * Class DAO which implements methods for get, create, update and delete token.
 */
package strilets.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import strilets.dao.AbstractDao;
import strilets.model.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class TokenRepositoryImpl extends AbstractDao<String, PersistentLogin> implements PersistentTokenRepository {

	static final Logger logger = LoggerFactory.getLogger(TokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		logger.info("Create token for user: {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		persist(persistentLogin);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info("Get token if any for series id: {}", seriesId);
		try {
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("series", seriesId));
			PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();

			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), persistentLogin.getLast_used());
		} catch (Exception e) {
			logger.info("Token not found.");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		logger.info("Delete token if any for user: {}", username);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
		if (persistentLogin != null) {
			logger.info("Remember me was selected.");
			delete(persistentLogin);
		}

	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		logger.info("Update token for seriesId: {}", seriesId);
		PersistentLogin persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		update(persistentLogin);
	}
}