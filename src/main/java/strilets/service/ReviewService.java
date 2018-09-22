package strilets.service;

import strilets.model.Review;

public interface ReviewService {

	Review getReviewById(Integer id);

	void saveReview(Review review);
}