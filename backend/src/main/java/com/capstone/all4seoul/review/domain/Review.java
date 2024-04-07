package com.capstone.all4seoul.review.domain;

import com.capstone.all4seoul.event.domain.Event;
import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "starRating", nullable = false, updatable = false)
    private Double starRating;

    @Column(name = "content", nullable = false, updatable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false, updatable = false)
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, updatable = false)
    private Event event;

    public static Review create(User user, String content, Double starRating) {
        Review review = new Review();
        review.user = user;
        review.content = content;
        review.starRating = starRating;
        review.place = null;
        return review;
    }

    /**
     * 연관관계 메서드
     */
    public void setUser(User user) {
        this.user = user;
        user.getReviews().add(this);
    }

    public void setPlace(Place place) {
        this.place = place;
        place.getReviews().add(this);
    }

    /**
     * 리뷰 수정 관련 메서드
     */
    public void updateContent(String content) {
        this.content = content;
    }

    public void updateStarRating(Double starRating) {
        this.starRating = starRating;
    }
}