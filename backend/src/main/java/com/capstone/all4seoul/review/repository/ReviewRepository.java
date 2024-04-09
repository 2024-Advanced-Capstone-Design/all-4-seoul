package com.capstone.all4seoul.review.repository;

import com.capstone.all4seoul.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserLoginId(String loginId);
    List<Review> findByPlaceId(Long placeId);
    List<Review> findByEventId(Long eventId);
}