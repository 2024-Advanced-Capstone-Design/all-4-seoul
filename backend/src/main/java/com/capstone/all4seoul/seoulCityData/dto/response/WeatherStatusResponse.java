package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.weather.WeatherStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeatherStatusResponse {
    private Long id;
    private String time;
    private String temperature;
    private String sensibleTemperature;
    private String maximumTemperature;
    private String minimumTemperature;
    private String humidity;
    private String precipitation;
    private String precipitationType;
    private String precipitationMessage;
    private String uvIndexLevel;
    private String uvIndex;
    private String uvMessage;
    private String pm25Index;
    private String pm25;
    private String pm10Index;
    private String pm10;
    private String airIndex;
    private String airIndexMvl;
    private String airIndexMain;
    private String airMessage;
    private List<WeatherForecastResponse> weatherForecasts;

    public static WeatherStatusResponse of(WeatherStatus weatherStatus) {
        WeatherStatusResponse response = new WeatherStatusResponse();

        response.id = weatherStatus.getId();
        response.time = weatherStatus.getTime();
        response.temperature = weatherStatus.getTemperature();
        response.sensibleTemperature = weatherStatus.getSensibleTemperature();
        response.maximumTemperature = weatherStatus.getMaximumTemperature();
        response.minimumTemperature = weatherStatus.getMinimumTemperature();
        response.humidity = weatherStatus.getHumidity();
        response.precipitation = weatherStatus.getPrecipitation();
        response.precipitationType = weatherStatus.getPrecipitationType();
        response.precipitationMessage = weatherStatus.getPrecipitationMessage();
        response.uvIndexLevel = weatherStatus.getUvIndexLevel();
        response.uvIndex = weatherStatus.getUvIndex();
        response.uvMessage = weatherStatus.getUvMessage();
        response.pm25Index = weatherStatus.getPm25Index();
        response.pm25 = weatherStatus.getPm25();
        response.pm10Index = weatherStatus.getPm10Index();
        response.pm10 = weatherStatus.getPm10();
        response.airIndex = weatherStatus.getAirIndex();
        response.airIndexMvl = weatherStatus.getAirIndexMvl();
        response.airIndexMain = weatherStatus.getAirIndexMain();
        response.airMessage = weatherStatus.getAirMessage();
        response.weatherForecasts = weatherStatus.getWeatherForecasts()
                .stream()
                .map(WeatherForecastResponse::of)
                .toList();

        return response;
    }
}
