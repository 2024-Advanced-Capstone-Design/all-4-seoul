package com.capstone.all4seoul.review.controller;

import com.capstone.all4seoul.review.dto.request.CreateReviewRequestForEvent;
import com.capstone.all4seoul.review.dto.request.CreateReviewRequestForPlace;
import com.capstone.all4seoul.review.dto.request.UpdateReviewRequest;
import com.capstone.all4seoul.review.dto.response.DetailReviewResponse;
import com.capstone.all4seoul.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 장소 리뷰 등록
     */
    @PostMapping("/places/{placeId}/reviews")
    public ResponseEntity<Object> createReviewForPlace(@PathVariable Long placeId, @RequestBody CreateReviewRequestForPlace request) {
        reviewService.createReviewForPlace(placeId, request);

        return ResponseEntity.noContent().build();
    }

    /**
     * 이벤트 리뷰 등록
     */
    @PostMapping("/events/{eventId}/reviews")
    public ResponseEntity<Object> createReviewForEvent(@PathVariable Long eventId, @RequestBody CreateReviewRequestForEvent request) {
        reviewService.createReviewForEvent(eventId, request);

        return ResponseEntity.noContent().build();
    }

    /**
     * 장소에 따른 리뷰 목록 조회
     */
    @GetMapping("/places/{placeId}/reviews")
    public List<DetailReviewResponse> listReviewsByPlace(@PathVariable Long placeId) {
        return reviewService.findReviewsByPlace(placeId);
    }

    @GetMapping("/reviews")
    public List<DetailReviewResponse> findAll() {
        return reviewService.findAll();
    }

    //리뷰 수정
    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UpdateReviewRequest request) {
        reviewService.updateReview(userId, request);

        return ResponseEntity.noContent().build();
    }

    //리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewService.findById(reviewId));

        return ResponseEntity.noContent().build();
    }
}
