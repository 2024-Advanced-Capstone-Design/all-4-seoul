package com.capstone.all4seoul.event.dto.response;

import com.capstone.all4seoul.event.domain.Event;
import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.review.dto.response.DetailReviewResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailEventResponse {

    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int price;
    private List<DetailReviewResponse> reviews;
    private EventPlaceInfo eventPlaceInfo; // 문화행사가 일어나는 장소에 관한 데이터

    public static DetailEventResponse of(Event event) {
        DetailEventResponse detailEventResponse = new DetailEventResponse();

        detailEventResponse.name = event.getName();
        detailEventResponse.startDateTime = event.getStartDateTime();
        detailEventResponse.endDateTime = event.getEndDateTime();
        detailEventResponse.price = event.getPrice();
        detailEventResponse.reviews = event.getReviews()
                .stream()
                .map(DetailReviewResponse::of)
                .toList();
        detailEventResponse.eventPlaceInfo = EventPlaceInfo.of(event.getPlace());

        return detailEventResponse;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class EventPlaceInfo {
        private String name;
        private String phoneNumber;
        private String address;
        private Double x;
        private Double y;

        public static EventPlaceInfo of(Place place) {
            EventPlaceInfo eventPlaceInfo = new EventPlaceInfo();

            eventPlaceInfo.name = place.getName();
            eventPlaceInfo.phoneNumber = place.getPhoneNumber();
            eventPlaceInfo.address = place.getAddress();
            eventPlaceInfo.x = place.getX();
            eventPlaceInfo.y = place.getY();

            return eventPlaceInfo;
        }
    }
}
