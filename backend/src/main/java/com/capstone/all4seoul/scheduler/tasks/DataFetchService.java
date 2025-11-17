package com.capstone.all4seoul.scheduler.tasks;

import com.capstone.all4seoul.place.domain.SeoulCityMajorPlaces;
import com.capstone.all4seoul.place.dto.response.externalApi.PlaceSearchResponseBySeoulDataApi;
import com.capstone.all4seoul.seoulCityData.domain.MajorPlace;
import com.capstone.all4seoul.seoulCityData.domain.charger.ChargerStation;
import com.capstone.all4seoul.seoulCityData.domain.event.AdjacentEvent;
import com.capstone.all4seoul.seoulCityData.domain.parkingLot.ParkingLot;
import com.capstone.all4seoul.seoulCityData.domain.population.LivePopulationStatus;
import com.capstone.all4seoul.seoulCityData.domain.weather.WeatherStatus;
import com.capstone.all4seoul.seoulCityData.repository.MajorPlaceRepository;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class DataFetchService {

    private final MajorPlaceRepository majorPlaceRepository;
    private static final int THREAD_COUNT = 100; // 스레드 풀의 크기

    /**
     * 서울시 실시간 데이터 병렬 처리 관련 로직
     */
    @Scheduled(fixedRate = 24, timeUnit = TimeUnit.HOURS)
    public void fetchAllData() {
        majorPlaceRepository.updateLatestFalseIfTrue();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<CompletableFuture<String>> futures = Arrays.stream(SeoulCityMajorPlaces.values())
                .map(areaName -> CompletableFuture.supplyAsync(
                                () -> fetchSeoulCityData(areaName.getAreaName()), executor)
                        .thenApply(areaNameForLog -> {
                            log.info("Successfully fetched data for area: " + areaNameForLog);
                            return areaNameForLog;
                        })
                )
                .toList();

        // 모든 CompletableFuture가 완료될 때까지 기다립니다.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        log.info("All data fetching tasks completed.");
    }

    public String fetchSeoulCityData(String areaName) {
        RestTemplate restTemplate = new RestTemplate();

        String seoulDataApiKey = "47725179416177663634557a734f45";
        String url = "http://openapi.seoul.go.kr:8088/" + seoulDataApiKey + "/json/citydata/1/5/" + areaName;

        ResponseEntity<PlaceSearchResponseBySeoulDataApi> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                PlaceSearchResponseBySeoulDataApi.class
        );

        PlaceSearchResponseBySeoulDataApi responseBody = responseEntity.getBody();
        if (responseBody == null || responseBody.getCityData() == null) {
            log.warn("CityData is null for area: {}", areaName);
            return areaName; // 그냥 건너뛰기
        }
        PlaceSearchResponseBySeoulDataApi.CityData cityData = responseBody.getCityData();
        majorPlaceRepository.save(
                new MajorPlace(
                        cityData.getAreaName(),
                        cityData.getAreaCode(),
                        savePopulationStatus(cityData),
                        saveParkingLots(cityData),
                        saveChargerStations(cityData),
                        saveWeatherStatus(cityData),
                        saveAdjacentEvents(cityData)
                )
        );

        return areaName;
    }

    //    @Scheduled(fixedRate = 24, timeUnit = TimeUnit.HOURS)
    private List<AdjacentEvent> saveAdjacentEvents(PlaceSearchResponseBySeoulDataApi.CityData cityData) {
        List<AdjacentEvent> adjacentEvents = new ArrayList<>();
        cityData.getAdjacentEvents()
                .forEach(event -> {
                    AdjacentEvent eventEntity = AdjacentEvent.createAdjacentEvent(event);
                    adjacentEvents.add(eventEntity);
                });

        return adjacentEvents;
    }

    //    @Scheduled(fixedRate = 24, timeUnit = TimeUnit.HOURS)
    private List<ParkingLot> saveParkingLots(PlaceSearchResponseBySeoulDataApi.CityData cityData) {
        List<ParkingLot> parkingLots = new ArrayList<>();
        cityData.getAdjacentParkingLots()
                .forEach(parkingLot -> {
                            ParkingLot parkingLotEntity = ParkingLot.createParkingLot(parkingLot);
                            parkingLots.add(parkingLotEntity);
                        }
                );

        return parkingLots;
    }

    //    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    private List<ChargerStation> saveChargerStations(PlaceSearchResponseBySeoulDataApi.CityData cityData) {
        return cityData.getAdjacentChargerStations().stream()
                .map(chargerStationDto -> {
                    List<ChargerStation.ChargerDetail> chargerDetails = Optional.ofNullable(
                                    chargerStationDto.getChargerDetails())
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(dto -> ChargerStation.ChargerDetail.createChargerDetail(dto))
                            .collect(Collectors.toList());

                    return new ChargerStation(chargerStationDto, chargerDetails);
                })
                .collect(Collectors.toList());
    }

    //    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    private List<LivePopulationStatus> savePopulationStatus(PlaceSearchResponseBySeoulDataApi.CityData cityData) {
        List<LivePopulationStatus> livePopulationStatuses = new ArrayList<>();
        cityData.getLivePopulationStatuses()
                .forEach(livePopulationStatus -> {
                            List<LivePopulationStatus.PopulationForecast> populationForecasts = new ArrayList<>();
                            livePopulationStatus.getForecastPopulation()
                                    .forEach(forecastPopulation -> populationForecasts.add(
                                                    LivePopulationStatus.PopulationForecast.createPopulationForecast(forecastPopulation)
                                            )
                                    );

                            livePopulationStatuses.add(
                                    new LivePopulationStatus(
                                            livePopulationStatus,
                                            populationForecasts
                                    )
                            );
                        }
                );

        return livePopulationStatuses;
    }

    //    @Scheduled(fixedRate = 15, timeUnit = TimeUnit.MINUTES)
    private List<WeatherStatus> saveWeatherStatus(PlaceSearchResponseBySeoulDataApi.CityData cityData) {
        List<WeatherStatus> weatherStatuses = new ArrayList<>();
        cityData.getWeatherStatuses()
                .forEach(weatherStatus -> {
                            List<WeatherStatus.WeatherForecast> weatherForecasts = new ArrayList<>();
                            weatherStatus.getWeatherForecasts()
                                    .forEach(weatherForecast -> weatherForecasts.add(
                                                    WeatherStatus.WeatherForecast.createWeatherForecast(
                                                            weatherForecast
                                                    )
                                            )
                                    );

                            weatherStatuses.add(
                                    new WeatherStatus(
                                            weatherStatus,
                                            weatherForecasts
                                    )
                            );
                        }
                );

        return weatherStatuses;
    }
}
