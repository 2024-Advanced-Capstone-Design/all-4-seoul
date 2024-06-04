package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.charger.ChargerStation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChargerStationResponse {
    private Long id;
    private String name;
    private String stationId;
    private String address;
    private String longitude;
    private String latitude;
    private String useTime;
    private String parkPay;
    private String limitYn;
    private String limitDetail;
    private String kindDetail; // 충전소 장소 유형 (ex. 사업장, 공영주차장)
    private List<ChargerDetailResponse> chargerDetails;

    public static ChargerStationResponse of(ChargerStation chargerStation) {
        ChargerStationResponse response = new ChargerStationResponse();

        response.id = chargerStation.getId();
        response.name = chargerStation.getName();
        response.stationId = chargerStation.getStationId();
        response.address = chargerStation.getAddress();
        response.longitude = chargerStation.getLongitude();
        response.latitude = chargerStation.getLatitude();
        response.useTime = chargerStation.getUseTime();
        response.parkPay = chargerStation.getParkPay();
        response.limitYn = chargerStation.getLimitYn();
        response.limitDetail = chargerStation.getLimitDetail();
        response.kindDetail = chargerStation.getKindDetail();
        response.chargerDetails = chargerStation.getChargerDetails()
                .stream()
                .map(ChargerDetailResponse::of)
                .toList();

        return response;
    }
}
