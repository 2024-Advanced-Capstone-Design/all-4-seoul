package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.event.AdjacentEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdjacentEventResponse {
    private Long id;
    private String name;
    private String period;
    private String place;
    private String longitude;
    private String latitude;
    private String payYn;
    private String thumbnail;
    private String url;
    private String etcDetail;

    public static AdjacentEventResponse of(AdjacentEvent adjacentEvent) {
        AdjacentEventResponse response = new AdjacentEventResponse();

        response.id = adjacentEvent.getId();
        response.name = adjacentEvent.getName();
        response.period = adjacentEvent.getPeriod();
        response.place = adjacentEvent.getPlace();
        response.longitude = adjacentEvent.getLongitude();
        response.latitude = adjacentEvent.getLatitude();
        response.payYn = adjacentEvent.getPayYn();
        response.thumbnail = adjacentEvent.getThumbnail();
        response.url = adjacentEvent.getUrl();
        response.etcDetail = adjacentEvent.getEtcDetail();

        return response;
    }
}
