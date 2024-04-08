package com.capstone.all4seoul.event.dto.response;

import com.capstone.all4seoul.event.domain.Event;
import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.review.dto.response.DetailReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailEventResponse {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int price;
    private List<DetailReviewResponse> reviews;

    // 문화행사가 일어나는 장소에 관한 데이터
    private String placeName;
    private String placePhoneNumber;
    private String placeAddress;
    private Double placeX;
    private Double placeY;
}
