/**
 * Class DAO which implements method for save review.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import strilets.model.Review;

@Repository("reviewDao")
public class ReviewDaoImpl extends AbstractDao<Integer, Review> implements ReviewDao {

	static final Logger logger = LoggerFactory.getLogger(ReviewDaoImpl.class);

	public void saveReview(Review review) {
		logger.info("Save review on user: {}", review.getUser().getLogin());
		persist(review);
	}
}