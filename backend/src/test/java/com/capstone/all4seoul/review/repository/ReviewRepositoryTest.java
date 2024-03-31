package com.capstone.all4seoul.review.repository;

import com.capstone.all4seoul.place.repository.PlaceRepository;
import com.capstone.all4seoul.review.domain.Review;
import com.capstone.all4seoul.user.domain.Gender;
import com.capstone.all4seoul.user.domain.Mbti;
import com.capstone.all4seoul.user.domain.User;
import com.capstone.all4seoul.user.repository.UserRepository;
import com.capstone.all4seoul.place.domain.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlaceRepository placeRepository;

    @BeforeEach
    void initData() {
        // 테스트를 위한 유저 생성
        User user = new User();
        user.setLoginId("testUser");
        user.setLoginPassword("test");
        user.setUsername("kim");
        user.setBirth(LocalDate.now());
        user.setMbti(Mbti.ISTJ);
        user.setGender(Gender.Male);
        user.setNickName("자기야");
        user.setCredit(0);
        userRepository.save(user);

        // 테스트를 위한 장소 생성
        Place place = new Place();
        place.setUser(user);
        place.setName("경기대학교");
        place.setReviews(reviewRepository.findByUserLoginId("testUser"));
        place.setPhoneNumber("112");
        place.setX("123.123");
        place.setY("456.567");
        placeRepository.save(place);

        Place place2 = new Place();
        place2.setUser(user);
        place2.setName("단국대학교");
        place2.setReviews(reviewRepository.findByUserLoginId("testUser"));
        place2.setPhoneNumber("119");
        place2.setX("111.111");
        place2.setY("222.222");
        placeRepository.save(place2);

        // 테스트를 위한 리뷰 생성
        Review review = new Review();
        review.setUser(user);
        review.setStarRating(4.5);
        review.setContent("This is a first test review.");
        review.setPlace(place);
        reviewRepository.save(review);

        Review review2 = new Review();
        review2.setUser(user);
        review2.setStarRating(5.0);
        review2.setContent("This is a second test review.");
        review2.setPlace(place2);
        reviewRepository.save(review2);
    }

    @Test
    void testFindByUserLoginId() {
        // 유저 아이디로 리뷰 조회
        List<Review> reviews = reviewRepository.findByUserLoginId("testUser");

        assertEquals(2, reviews.size());
        assertEquals(reviews.get(0).getUser().getLoginId(),reviews.get(1).getUser().getLoginId());
    }

    @Test
    public void testFindByPlaceName() {
        //장소 이름으로 리뷰 조회
        Place findPlace = placeRepository.findByName("경기대학교");
        Place findPlace2 = placeRepository.findByName("단국대학교");

        assertThat(findPlace.getReviews().get(0).getStarRating()).isEqualTo(4.5);
        assertThat(findPlace2.getReviews().get(0).getStarRating()).isEqualTo(5.0);

        assertThat(findPlace.getPhoneNumber()).isEqualTo("112");
        assertThat(findPlace2.getPhoneNumber()).isEqualTo("119");

        assertEquals(findPlace.getUser().getUsername(), "kim");
        assertEquals(findPlace2.getUser().getUsername(), "kim");
    }
}