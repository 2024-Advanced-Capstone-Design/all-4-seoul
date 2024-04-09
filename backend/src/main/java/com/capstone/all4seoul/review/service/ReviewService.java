package com.capstone.all4seoul.review.service;

import com.capstone.all4seoul.event.domain.Event;
import com.capstone.all4seoul.event.repository.EventRepository;
import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.place.repository.PlaceRepository;
import com.capstone.all4seoul.review.domain.Review;
import com.capstone.all4seoul.review.dto.request.CreateReviewRequestForEvent;
import com.capstone.all4seoul.review.dto.request.CreateReviewRequestForPlace;
import com.capstone.all4seoul.review.dto.request.UpdateReviewRequest;
import com.capstone.all4seoul.review.dto.response.DetailReviewResponse;
import com.capstone.all4seoul.review.repository.ReviewRepository;
import com.capstone.all4seoul.user.domain.User;
import com.capstone.all4seoul.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;

    //장소기반
    @Transactional
    public Long createReviewForPlace(Long placeId, CreateReviewRequestForPlace request) {
        User user = userRepository.findById(request.getId()).get();
        Place place = placeRepository.findById(placeId).get();
        Review review = Review.createReviewForPlace(user, place, request.getContent(), request.getStarRating());
        reviewRepository.save(review);

        return review.getId();
    }

    @Transactional
    public Long createReviewForEvent(Long eventId, CreateReviewRequestForEvent request) {
        User user = userRepository.findById(request.getId()).get();
        Event event = eventRepository.findById(eventId).get();
        Review review = Review.createReviewForEvent(user, event, request.getContent(), request.getStarRating());
        reviewRepository.save(review);

        return review.getId();
    }

    //리뷰 단건 조회
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId).get();
    }

    //리뷰 리스트 조회
    public List<DetailReviewResponse> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(DetailReviewResponse::of)
                .toList();
    }

    //특정 장소의 리뷰 목록 조회
    public List<DetailReviewResponse> findReviewsByPlace(Long placeId) {
        return reviewRepository.findByPlaceId(placeId)
                .stream()
                .map(DetailReviewResponse::of)
                .toList();
    }

    //리뷰 업데이트
    @Transactional
    public void updateReview(Long reviewId, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(reviewId).get();
        review.updateContent(request.getContent());
        review.updateStarRating(request.getStarRating());

    }

    //리뷰 삭제
    @Transactional
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }

}
