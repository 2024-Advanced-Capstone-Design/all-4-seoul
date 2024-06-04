package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.population.LivePopulationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LivePopulationStatusResponse {
    private Long id;
    private String areaCongestLevel;
    private String areaCongestMessage;
    private String minimumAreaPopulation;
    private String maximumAreaPopulation;
    private String malePopulationRate;
    private String femalePopulationRate;
    private String populationRate0;
    private String populationRate10;
    private String populationRate20;
    private String populationRate30;
    private String populationRate40;
    private String populationRate50;
    private String populationRate60;
    private String populationRate70;
    private String resentPopulationRate;
    private String nonResentPopulationRate;
    private String replaceYN;
    private String populationTime;
    private String forecastYN;
    private List<PopulationForecastResponse> populationForecasts = new ArrayList<>();

    public static LivePopulationStatusResponse of(LivePopulationStatus livePopulationStatus) {
        LivePopulationStatusResponse livePopulationStatusResponse = new LivePopulationStatusResponse();

        livePopulationStatusResponse.id = livePopulationStatus.getId();
        livePopulationStatusResponse.areaCongestLevel = livePopulationStatus.getAreaCongestLevel();
        livePopulationStatusResponse.areaCongestMessage = livePopulationStatus.getAreaCongestMessage();
        livePopulationStatusResponse.minimumAreaPopulation = livePopulationStatus.getMinimumAreaPopulation();
        livePopulationStatusResponse.maximumAreaPopulation = livePopulationStatus.getMaximumAreaPopulation();
        livePopulationStatusResponse.malePopulationRate = livePopulationStatus.getMalePopulationRate();
        livePopulationStatusResponse.femalePopulationRate = livePopulationStatus.getFemalePopulationRate();
        livePopulationStatusResponse.populationRate0 = livePopulationStatus.getPopulationRate0();
        livePopulationStatusResponse.populationRate10 = livePopulationStatus.getPopulationRate10();
        livePopulationStatusResponse.populationRate20 = livePopulationStatus.getPopulationRate20();
        livePopulationStatusResponse.populationRate30 = livePopulationStatus.getPopulationRate30();
        livePopulationStatusResponse.populationRate40 = livePopulationStatus.getPopulationRate40();
        livePopulationStatusResponse.populationRate50 = livePopulationStatus.getPopulationRate50();
        livePopulationStatusResponse.populationRate60 = livePopulationStatus.getPopulationRate60();
        livePopulationStatusResponse.populationRate70 = livePopulationStatus.getPopulationRate70();
        livePopulationStatusResponse.resentPopulationRate = livePopulationStatus.getResentPopulationRate();
        livePopulationStatusResponse.nonResentPopulationRate = livePopulationStatus.getNonResentPopulationRate();
        livePopulationStatusResponse.replaceYN = livePopulationStatus.getReplaceYN();
        livePopulationStatusResponse.populationTime = livePopulationStatus.getPopulationTime();
        livePopulationStatusResponse.forecastYN = livePopulationStatus.getForecastYN();

        livePopulationStatusResponse.populationForecasts = livePopulationStatus.getPopulationForecasts()
                .stream()
                .map(PopulationForecastResponse::of)
                .toList();

        return livePopulationStatusResponse;
    }
}
