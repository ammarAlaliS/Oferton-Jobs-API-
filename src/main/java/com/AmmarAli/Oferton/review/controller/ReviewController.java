package com.AmmarAli.Oferton.review.controller;

import com.AmmarAli.Oferton.review.entities.Review;
import com.AmmarAli.Oferton.review.repositories.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }
    @PostMapping("/review")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isReviewSave = reviewService.addReview(companyId, review);
        if (isReviewSave){
            return new ResponseEntity<>("review created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company does not exit", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/review/{reviewId}")
    public  ResponseEntity<?> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review isReviewExit = reviewService.getReview(companyId, reviewId);
        if (isReviewExit != null){
            return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
        }
        return new ResponseEntity<>("review does not exits", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<String>  updateReview(@PathVariable Long companyId,
                                                @PathVariable Long reviewId,
                                                @RequestBody Review review){
        boolean isReviewUpdate = reviewService.updateReview(companyId,reviewId , review);
        if (isReviewUpdate){
            return new ResponseEntity<>("review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("review does not exits", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId,reviewId);
        if (isReviewDeleted){
            return new ResponseEntity<>("review Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("review does not exits", HttpStatus.NOT_FOUND);
    }
}
