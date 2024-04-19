package com.capstone.all4seoul.place.service;

import com.capstone.all4seoul.place.dto.request.PlaceSearchRequest;
import com.capstone.all4seoul.place.dto.response.PlaceSearchResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlaceServiceByGoogle {

    private static String googleApiKey = "AIzaSyDQ4l4GsWb9nJpXCzfXV7igshaJ8U1aopE";

    public static PlaceSearchResponse searchPlaces(PlaceSearchRequest placeSearchRequest) {
        String url = "https://places.googleapis.com/v1/places:searchNearby";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Goog-Api-Key", googleApiKey);
        headers.set("X-Goog-FieldMask", "places.displayName,places.id,places.currentOpeningHours,places.nationalPhoneNumber,places.reviews,places.rating,places.websiteUri");

        HttpEntity<PlaceSearchRequest> requestEntity = new HttpEntity<>(placeSearchRequest, headers);

        // HTTP 요청 보내기
        ResponseEntity<PlaceSearchResponse> responseEntity = new RestTemplate().exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                PlaceSearchResponse.class
        );

        // 응답 처리
        return responseEntity.getBody();
    }
}
