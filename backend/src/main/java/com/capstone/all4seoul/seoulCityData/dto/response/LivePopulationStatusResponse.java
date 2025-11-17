package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.population.LivePopulationStatus;
import java.time.LocalDateTime;
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
    private Integer minimumAreaPopulation;
    private Integer maximumAreaPopulation;
    private Double malePopulationRate;
    private Double femalePopulationRate;
    private Double populationRate0;
    private Double populationRate10;
    private Double populationRate20;
    private Double populationRate30;
    private Double populationRate40;
    private Double populationRate50;
    private Double populationRate60;
    private Double populationRate70;
    private Double resentPopulationRate;
    private Double nonResentPopulationRate;
    private String replaceYN;
    private LocalDateTime populationTime;
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
