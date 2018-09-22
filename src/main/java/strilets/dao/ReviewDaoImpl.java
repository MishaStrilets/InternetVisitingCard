package strilets.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import strilets.model.Review;

@Repository("reviewDao")
public class ReviewDaoImpl extends AbstractDao<Integer, Review> implements ReviewDao {

	static final Logger logger = LoggerFactory.getLogger(ReviewDaoImpl.class);

	public Review getReviewById(Integer id) {
		Review review = getByKey(id);
		return review;
	}

	public void saveReview(Review review) {
		logger.info("Save review: {}", review);
		persist(review);	
	}
}