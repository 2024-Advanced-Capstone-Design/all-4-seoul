package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.charger.ChargerStation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChargerDetailResponse {
    private Long id;
    private String chargerId;
    private String type;
    private String status;

    public static ChargerDetailResponse of(ChargerStation.ChargerDetail chargerDetail) {
        ChargerDetailResponse response = new ChargerDetailResponse();

        response.id = chargerDetail.getId();
        response.chargerId = chargerDetail.getChargerId();
        response.type = chargerDetail.getType();
        response.status = chargerDetail.getStatus();

        return response;

    }
}
