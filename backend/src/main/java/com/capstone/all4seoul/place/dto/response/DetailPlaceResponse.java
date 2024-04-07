package com.capstone.all4seoul.place.dto.response;

import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailPlaceResponse {
    private String name;
    private List<Review> reviews;
    private String phoneNumber;

    public static DetailPlaceResponse of(Place place) {
        DetailPlaceResponse detailPlaceResponse = new DetailPlaceResponse();

        detailPlaceResponse.name = place.getName();
        detailPlaceResponse.reviews = place.getReviews();
        detailPlaceResponse.phoneNumber = place.getPhoneNumber();

        return detailPlaceResponse;
    }
}
