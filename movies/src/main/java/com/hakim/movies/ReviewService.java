package com.hakim.movies;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;
	private MongoTemplate mongoTemplate;
	
	public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate) {
		this.reviewRepository = reviewRepository;
		this.mongoTemplate = mongoTemplate;
	}
	
	public Review createReview(String reviewBody, String imdbId) {
		Review review = reviewRepository.insert(new Review(reviewBody));
		
		
		mongoTemplate.update(Movie.class)
			.matching(Criteria.where("imdbId").is(imdbId))
			.apply(new Update().push("reviews").value(review))
			.first();
		
		return review;
	}
}
