/**
 * Interface which contains abstract method for save review.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.dao;

import strilets.model.Review;

public interface ReviewDao {

	void saveReview(Review review);
}