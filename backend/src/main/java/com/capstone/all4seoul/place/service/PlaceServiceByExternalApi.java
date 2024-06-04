package com.capstone.all4seoul.place.service;

import com.capstone.all4seoul.place.dto.request.PlaceNearbySearchRequestByGoogle;
import com.capstone.all4seoul.place.dto.request.PlaceSearchRequestWithTextQueryByGoogle;
import com.capstone.all4seoul.place.dto.response.MajorPlaceResponse;
import com.capstone.all4seoul.place.dto.response.externalApi.PlaceImageSearchResponseByGoogle;
import com.capstone.all4seoul.place.dto.response.externalApi.PlaceNearbySearchResponseByGoogle;
import com.capstone.all4seoul.place.dto.response.externalApi.PlaceSearchResponseBySeoulDataApi;
import com.capstone.all4seoul.place.dto.response.externalApi.PlaceTextSearchResponseByGoogle;
import com.capstone.all4seoul.seoulCityData.repository.MajorPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceByExternalApi {

    private static String googleApiKey = "AIzaSyDQ4l4GsWb9nJpXCzfXV7igshaJ8U1aopE";
    private static String seoulDataApiKey = "47725179416177663634557a734f45";
    private final MajorPlaceRepository majorPlaceRepository;

    public PlaceTextSearchResponseByGoogle searchPlacesByTextQuery(String textQuery) {
        String url = "https://places.googleapis.com/v1/places:searchText";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Goog-Api-Key", googleApiKey);
        headers.set("X-Goog-FieldMask",
                "places.displayName," +
                        "places.formattedAddress," +
                        "places.location,places." +
                        "photos,places.types," +
                        "places.nationalPhoneNumber," +
                        "places.regularOpeningHours," +
                        "places.priceLevel," +
                        "places.rating," +
                        "places.userRatingCount," +
                        "places.websiteUri," +
                        "places.reviews");

        PlaceSearchRequestWithTextQueryByGoogle request = new PlaceSearchRequestWithTextQueryByGoogle(textQuery, "ko", 1);
        HttpEntity<PlaceSearchRequestWithTextQueryByGoogle> requestEntity = new HttpEntity<>(request, headers);

        // HTTP 요청 보내기
        ResponseEntity<PlaceTextSearchResponseByGoogle> responseEntity = new RestTemplate().exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                PlaceTextSearchResponseByGoogle.class
        );

        // HTTP 요청 보내기
        if (responseEntity.getBody().getPlaces().get(0).getPhotos() == null) {
            responseEntity.getBody().getPlaces().get(0).setPhotos(new ArrayList<>());
        } else {
            responseEntity.getBody().getPlaces().get(0).getPhotos()
                    .forEach(photo -> {
                        String photoUrl = "https://places.googleapis.com/v1/" + photo.getName() + "/media?maxHeightPx=4800&maxWidthPx=4800&key=" + googleApiKey + "&skipHttpRedirect=true";

                        ResponseEntity<PlaceImageSearchResponseByGoogle> photoUrlResponse = new RestTemplate().exchange(
                                photoUrl,
                                HttpMethod.GET,
                                new HttpEntity<>(new HttpHeaders()),
                                PlaceImageSearchResponseByGoogle.class
                        );
                        photo.setPhotoUri(photoUrlResponse.getBody().getPhotoUri());
                    });
        }

        // 응답 처리
        return responseEntity.getBody();
    }

    public MajorPlaceResponse getMajorPlace(String areaName) {
        if (isMajorPlace(areaName)) {
            return MajorPlaceResponse.of(
                    majorPlaceRepository.findFirstByAreaNameContains(areaName)
                            .orElseThrow(() -> new IllegalArgumentException("해당하는 주요 장소가 존재하지 않습니다."))
            );
        }
        return null;
    }

    private boolean isMajorPlace(String areaName) {
        return majorPlaceRepository.existsByAreaNameContains(areaName);
    }

    public PlaceNearbySearchResponseByGoogle searchPlaces(PlaceNearbySearchRequestByGoogle placeNearbySearchRequestByGoogle) {
        String url = "https://places.googleapis.com/v1/places:searchNearby";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Goog-Api-Key", googleApiKey);
        headers.set("X-Goog-FieldMask", "places.displayName,places.id,places.currentOpeningHours,places.nationalPhoneNumber,places.reviews,places.rating,places.websiteUri");

        HttpEntity<PlaceNearbySearchRequestByGoogle> requestEntity = new HttpEntity<>(placeNearbySearchRequestByGoogle, headers);

        // HTTP 요청 보내기
        ResponseEntity<PlaceNearbySearchResponseByGoogle> responseEntity = new RestTemplate().exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                PlaceNearbySearchResponseByGoogle.class
        );

        // 응답 처리
        return responseEntity.getBody();
    }

    public PlaceSearchResponseBySeoulDataApi searchPlacesBySeoulDataApi(String keyword) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://openapi.seoul.go.kr:8088/" + seoulDataApiKey + "/json/citydata/1/5/" + keyword;

        ResponseEntity<PlaceSearchResponseBySeoulDataApi> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                PlaceSearchResponseBySeoulDataApi.class
        );

        return responseEntity.getBody();
    }
}
