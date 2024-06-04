package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.weather.WeatherStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeatherForecastResponse {
    private Long id;
    private String dayTime;
    private String temperature24Hour;
    private String precipitation;
    private String precipitationType;
    private String rainChance;
    private String skyStatus;

    public static WeatherForecastResponse of(WeatherStatus.WeatherForecast weatherForecast) {
        WeatherForecastResponse response = new WeatherForecastResponse();

        response.id = weatherForecast.getId();
        response.dayTime = weatherForecast.getDayTime();
        response.temperature24Hour = weatherForecast.getTemperature24Hour();
        response.precipitation = weatherForecast.getPrecipitation();
        response.precipitationType = weatherForecast.getPrecipitationType();
        response.rainChance = weatherForecast.getRainChance();
        response.skyStatus = weatherForecast.getSkyStatus();

        return response;
    }
}
