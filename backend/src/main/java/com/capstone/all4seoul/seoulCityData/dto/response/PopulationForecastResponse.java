package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.population.LivePopulationStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopulationForecastResponse {
    private Long id;
    private LocalDateTime time;
    private String congestLevel;
    private Integer minimumForecastPopulation;
    private Integer maximumForecastPopulation;

    public static PopulationForecastResponse of(LivePopulationStatus.PopulationForecast populationForecast) {
        PopulationForecastResponse response = new PopulationForecastResponse();

        response.id = populationForecast.getId();
        response.time = populationForecast.getTime();
        response.congestLevel = populationForecast.getCongestLevel();
        response.minimumForecastPopulation = populationForecast.getMinimumForecastPopulation();
        response.maximumForecastPopulation = populationForecast.getMaximumForecastPopulation();

        return response;
    }
}
