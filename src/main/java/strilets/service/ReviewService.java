/**
 * Interface which contains abstract methods for business logic review.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.service;

import strilets.model.Review;

public interface ReviewService {

	void saveReview(Review review);
}