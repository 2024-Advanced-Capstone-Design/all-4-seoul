package com.capstone.all4seoul.place.dto.response;

import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.review.dto.response.DetailReviewResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailPlaceResponse {
    private String name;
    private String phoneNumber;
    private int bookmarkCount;
    private List<DetailReviewResponse> reviews;

    public static DetailPlaceResponse of(Place place) {
        DetailPlaceResponse detailPlaceResponse = new DetailPlaceResponse();

        detailPlaceResponse.name = place.getName();
        detailPlaceResponse.phoneNumber = place.getPhoneNumber();
        detailPlaceResponse.bookmarkCount = place.getBookmarks().size();
        detailPlaceResponse.reviews = place.getReviews().stream()
                .map(DetailReviewResponse::of)
                .toList();

        return detailPlaceResponse;
    }
}
