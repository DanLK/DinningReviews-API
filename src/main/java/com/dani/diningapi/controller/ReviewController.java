package com.dani.diningapi.controller;

import com.dani.diningapi.enums.ReviewStatus;
import com.dani.diningapi.model.Restaurant;
import com.dani.diningapi.model.Review;
import com.dani.diningapi.repository.RestaurantRepository;
import com.dani.diningapi.repository.ReviewRepository;
import com.dani.diningapi.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewController(final ReviewRepository reviewRepository, final UserRepository userRepository,
                            final RestaurantRepository restaurantRepository){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/reviews/byRestaurant")
    public List<Review> getApproved(@RequestParam(name="restaurantId", required = false) Long restaurantId){
        if (restaurantId != null){
            return this.reviewRepository.findByStatusAndRestaurantId(ReviewStatus.APPROVED, restaurantId);
        }
        return new ArrayList<>();
    }

    @GetMapping("/admin/pendingReviews")
    public List<Review> getPendingReviews(){
        return this.reviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    @PostMapping("/reviews/new")
    public String addNewReview(@RequestBody Review newReview){
        if (!this.userRepository.findByUserName(newReview.getAuthorName()).isEmpty() &&
            this.restaurantRepository.findById(newReview.getRestaurantId()).isPresent()){
            if (newReview.getStatus() != ReviewStatus.PENDING){
                return "Error. Review Status Must Be Pending.";
            }
            if (newReview.getPeanutScore() != null && (newReview.getPeanutScore() < 0 || newReview.getPeanutScore() > 5)){
                return "Error. Invalid peanut score.";
            }
            if (newReview.getEggScore() != null && (newReview.getEggScore() < 0 || newReview.getEggScore() > 5)){
                return "Error. Invalid egg score.";
            }
            if (newReview.getDairyScore() != null && (newReview.getDairyScore() < 0 || newReview.getDairyScore() > 5)){
                return "Error. Invalid dairy score.";
            }
            this.reviewRepository.save(newReview);
            return "Review added successfully.";
        }
        return "Error. Could not add review. Invalid author or restaurant.";
    }

    @PutMapping("/admin/editReviewStatus/{id}")
    public String editReviewStatus(@PathVariable Long id, @RequestParam(name="status") ReviewStatus status){
        if (status != null){
            if (this.reviewRepository.findById(id).isPresent()){
                Review reviewToUpdate = this.reviewRepository.findById(id).get();
                if (reviewToUpdate.getStatus() != ReviewStatus.PENDING){
                    return "Error. The status was already defined.";
                }
                reviewToUpdate.setStatus(status);
                this.reviewRepository.save(reviewToUpdate);
                // Update restaurant scores
                if (this.restaurantRepository.findById(reviewToUpdate.getRestaurantId()).isPresent()){
                    Restaurant restaurantToUpdate = this.restaurantRepository.findById(reviewToUpdate.getRestaurantId()).get();
                    if (reviewToUpdate.getPeanutScore() != null){
                        Integer previousScoresCount =
                                this.reviewRepository.findByStatusAndRestaurantIdAndPeanutScoreNotNull(ReviewStatus.APPROVED, reviewToUpdate.getRestaurantId()).size();
                        restaurantToUpdate.updatePeanutScore(reviewToUpdate.getPeanutScore(), previousScoresCount);
                    }
                    if (reviewToUpdate.getEggScore() != null){
                        Integer previousScoresCount =
                                this.reviewRepository.findByStatusAndRestaurantIdAndEggScoreNotNull(ReviewStatus.APPROVED, reviewToUpdate.getRestaurantId()).size();
                        restaurantToUpdate.updateEggScore(reviewToUpdate.getEggScore(), previousScoresCount);
                    }
                    if (reviewToUpdate.getDairyScore() != null){
                        Integer previousScoresCount =
                                this.reviewRepository.findByStatusAndRestaurantIdAndDairyScoreNotNull(ReviewStatus.APPROVED, reviewToUpdate.getRestaurantId()).size();
                        restaurantToUpdate.updateDairyScore(reviewToUpdate.getDairyScore(), previousScoresCount);
                    }
                    restaurantToUpdate.updateOverallScore();
                    this.restaurantRepository.save(restaurantToUpdate);
                }
                return "Review status updated successfully.";
            }
            return "Error. Review non-existent.";
        }
        return "Couldn't update review status.";
    }

}
