package com.capstone.all4seoul.place.dto.response;

import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.review.dto.response.DetailReviewResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailPlaceResponse {
    private String name;
    private List<DetailReviewResponse> reviews;
    private String phoneNumber;

    public static DetailPlaceResponse of(Place place) {
        DetailPlaceResponse detailPlaceResponse = new DetailPlaceResponse();

        detailPlaceResponse.name = place.getName();

        // Place에 있는 Review 객체들을 DetailReviewResponse 객체로 변환하여 리스트에 담음
        List<DetailReviewResponse> reviewResponses = place.getReviews().stream()
                .map(review -> DetailReviewResponse.of(review))
                .collect(Collectors.toList());
        detailPlaceResponse.reviews = reviewResponses;

        detailPlaceResponse.phoneNumber = place.getPhoneNumber();

        return detailPlaceResponse;
    }
}
