package com.dani.diningapi.repository;

import com.dani.diningapi.enums.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import com.dani.diningapi.model.Review;
import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByStatus(ReviewStatus status);
    List<Review> findByStatusAndRestaurantId(ReviewStatus status, Long restaurantId);
    List<Review> findByRestaurantId(Integer restaurantId);
    List<Review> findByAuthorName(String authorName);
    List<Review> findAll();
    List<Review> findByStatusAndRestaurantIdAndPeanutScoreNotNull(ReviewStatus status, Long restaurantId);
    List<Review> findByStatusAndRestaurantIdAndEggScoreNotNull(ReviewStatus status, Long restaurantId);
    List<Review> findByStatusAndRestaurantIdAndDairyScoreNotNull(ReviewStatus status, Long restaurantId);
}
