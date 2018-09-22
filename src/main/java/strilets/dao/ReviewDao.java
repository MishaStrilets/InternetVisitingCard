package strilets.dao;

import strilets.model.Review;

public interface ReviewDao {

	Review getReviewById(Integer id);

	void saveReview(Review review);
}