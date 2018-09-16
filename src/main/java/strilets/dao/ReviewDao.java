package strilets.dao;

import java.util.List;

import strilets.model.Review;

public interface ReviewDao {

	Review getReviewById(Integer id);

	void saveReview(Review review);
	
	void deleteReview(Integer userId);

	List<Review> getAllReviews();
}