package strilets.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
	
	public void deleteReview(Integer userId) {
		logger.info("Delete review: {}", userId);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("user_id", userId));
		Review review = (Review) criteria.uniqueResult();
		delete(review);
	}

	public List<Review> getAllReviews() {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<Review> reviews = criteria.list();
		return reviews;
	}
}