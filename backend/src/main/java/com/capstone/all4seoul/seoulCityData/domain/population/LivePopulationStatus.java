package com.capstone.all4seoul.seoulCityData.domain.population;

import com.capstone.all4seoul.place.dto.response.externalApi.PlaceSearchResponseBySeoulDataApi;
import com.capstone.all4seoul.seoulCityData.domain.MajorPlace;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "live_population_statuses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LivePopulationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "live_population_statuses_id")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_place_id")
    private MajorPlace majorPlace;

    @OneToMany(mappedBy = "livePopulationStatus", cascade = CascadeType.ALL)
    private List<PopulationForecast> populationForecasts = new ArrayList<>();

    public LivePopulationStatus(
            PlaceSearchResponseBySeoulDataApi.CityData.LivePopulationStatus fetchedLivePopulationStatus,
            List<PopulationForecast> populationForecasts
    ) {
        this.areaCongestLevel = fetchedLivePopulationStatus.getAreaCongestLevel();
        this.areaCongestMessage = fetchedLivePopulationStatus.getAreaCongestMessage();
        this.minimumAreaPopulation = fetchedLivePopulationStatus.getMinimumAreaPopulation();
        this.maximumAreaPopulation = fetchedLivePopulationStatus.getMaximumAreaPopulation();
        this.malePopulationRate = fetchedLivePopulationStatus.getMalePopulationRate();
        this.femalePopulationRate = fetchedLivePopulationStatus.getFemalePopulationRate();
        this.populationRate0 = fetchedLivePopulationStatus.getPopulationRate0();
        this.populationRate10 = fetchedLivePopulationStatus.getPopulationRate10();
        this.populationRate20 = fetchedLivePopulationStatus.getPopulationRate20();
        this.populationRate30 = fetchedLivePopulationStatus.getPopulationRate30();
        this.populationRate40 = fetchedLivePopulationStatus.getPopulationRate40();
        this.populationRate50 = fetchedLivePopulationStatus.getPopulationRate50();
        this.populationRate60 = fetchedLivePopulationStatus.getPopulationRate60();
        this.populationRate70 = fetchedLivePopulationStatus.getPopulationRate70();
        this.resentPopulationRate = fetchedLivePopulationStatus.getResentPopulationRate();
        this.nonResentPopulationRate = fetchedLivePopulationStatus.getNonResentPopulationRate();
        this.replaceYN = fetchedLivePopulationStatus.getReplaceYn();
        this.populationTime = fetchedLivePopulationStatus.getPopulationTime();
        this.forecastYN = fetchedLivePopulationStatus.getForecastYn();
        setPopulationForecasts(populationForecasts);
    }

    public void setMajorPlace(MajorPlace majorPlace) {
        this.majorPlace = majorPlace;
        majorPlace.getLivePopulationStatuses().add(this);
    }

    private void setPopulationForecasts(List<PopulationForecast> populationForecasts) {
        populationForecasts.stream()
                .forEach(populationForecast -> populationForecast.setLivePopulationStatus(this));
    }

    @Entity
    @Getter
    @Table(name = "population_forecasts")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PopulationForecast {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "population_forecast_id")
        private Long id;

        private String time;

        private String congestLevel;

        private String minimumForecastPopulation;

        private String maximumForecastPopulation;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "live_population_status_id")
        private LivePopulationStatus livePopulationStatus;

        public static PopulationForecast createPopulationForecast(
                PlaceSearchResponseBySeoulDataApi.CityData.LivePopulationStatus.ForecastPopulation fetchedForecastPopulation
        ) {
            PopulationForecast populationForecast = new PopulationForecast();

            populationForecast.time = fetchedForecastPopulation.getTime();
            populationForecast.congestLevel = fetchedForecastPopulation.getCongestLevel();
            populationForecast.minimumForecastPopulation = fetchedForecastPopulation.getMinimumForecastPopulation();
            populationForecast.maximumForecastPopulation = fetchedForecastPopulation.getMaximumForecastPopulation();

            return populationForecast;
        }

        private void setLivePopulationStatus(LivePopulationStatus livePopulationStatus) {
            this.livePopulationStatus = livePopulationStatus;
            livePopulationStatus.getPopulationForecasts().add(this);
        }
    }
}


