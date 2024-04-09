package com.capstone.all4seoul.review.repository;

import com.capstone.all4seoul.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserLoginId(String loginId);

    //장소 이름으로 리뷰 리스트 조회
    List<Review> findByPlaceName(String placeId);
    List<Review> findByPlaceId(Long placeId);
}