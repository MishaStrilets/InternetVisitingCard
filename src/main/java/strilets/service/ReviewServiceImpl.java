package strilets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import strilets.dao.ReviewDao;
import strilets.model.Review;

@Service("reviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao dao;

	public Review getReviewById(Integer id) {
		return dao.getReviewById(id);
	}

	public void saveReview(Review review) {
		dao.saveReview(review);
	}
}