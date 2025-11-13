package com.capstone.all4seoul.place.dto.response.externalApi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchResponseBySeoulDataApi {
    @JsonProperty("list_total_count")
    private Long listTotalCount;
    @JsonProperty("RESULT")
    private Result result;
    @JsonProperty("CITYDATA")
    private CityData cityData;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        @JsonProperty("RESULT.CODE")
        private String resultCode;
        @JsonProperty("RESULT.MESSAGE")
        private String resultMessage;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CityData {
        @JsonProperty("AREA_NM")
        private String areaName;
        @JsonProperty("AREA_CD")
        private String areaCode;
        @JsonProperty("LIVE_PPLTN_STTS")
        private List<LivePopulationStatus> livePopulationStatuses;
        @JsonProperty("ROAD_TRAFFIC_STTS")
        private RoadTrafficStatus roadTrafficStatus;
        @JsonProperty("PRK_STTS")
        private List<ParkingLot> adjacentParkingLots;
        @JsonProperty("SUB_STTS")
        private List<Sub> SUB_STTS;
        @JsonProperty("BUS_STN_STTS")
        private List<Bus> BUS_STN_STTS;
        @JsonProperty("ACDNT_CNTRL_STTS")
        private List<Object> ACDNT_CNTRL_STTS;
        @JsonProperty("SBIKE_STTS")
        private List<Object> SBIKE_STTS;
        @JsonProperty("WEATHER_STTS")
        private List<WeatherStatus> weatherStatuses;
        @JsonProperty("CHARGER_STTS")
        private List<ChargerStation> adjacentChargerStations;
        @JsonProperty("EVENT_STTS")
        private List<AdjacentEvent> adjacentEvents;
        @JsonProperty("COVID_19_STTS")
        private String COVID_19_STTS;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class LivePopulationStatus {
            @JsonProperty("AREA_CONGEST_LVL")
            private String areaCongestLevel;
            @JsonProperty("AREA_CONGEST_MSG")
            private String areaCongestMessage;
            @JsonProperty("AREA_PPLTN_MIN")
            private Integer minimumAreaPopulation;
            @JsonProperty("AREA_PPLTN_MAX")
            private Integer maximumAreaPopulation;
            @JsonProperty("MALE_PPLTN_RATE")
            private Double malePopulationRate;
            @JsonProperty("FEMALE_PPLTN_RATE")
            private Double femalePopulationRate;
            @JsonProperty("PPLTN_RATE_0")
            private Double populationRate0;
            @JsonProperty("PPLTN_RATE_10")
            private Double populationRate10;
            @JsonProperty("PPLTN_RATE_20")
            private Double populationRate20;
            @JsonProperty("PPLTN_RATE_30")
            private Double populationRate30;
            @JsonProperty("PPLTN_RATE_40")
            private Double populationRate40;
            @JsonProperty("PPLTN_RATE_50")
            private Double populationRate50;
            @JsonProperty("PPLTN_RATE_60")
            private Double populationRate60;
            @JsonProperty("PPLTN_RATE_70")
            private Double populationRate70;
            @JsonProperty("RESNT_PPLTN_RATE")
            private Double resentPopulationRate;
            @JsonProperty("NON_RESNT_PPLTN_RATE")
            private Double nonResentPopulationRate;
            @JsonProperty("REPLACE_YN")
            private String replaceYn;
            @JsonProperty("PPLTN_TIME")
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
            private LocalDateTime populationTime;
            @JsonProperty("FCST_YN")
            private String forecastYn;
            @JsonProperty("FCST_PPLTN")
            private List<ForecastPopulation> forecastPopulation;

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class ForecastPopulation {
                @JsonProperty("FCST_TIME")
                @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                private LocalDateTime time;
                @JsonProperty("FCST_CONGEST_LVL")
                private String congestLevel;
                @JsonProperty("FCST_PPLTN_MIN")
                private Integer minimumForecastPopulation;
                @JsonProperty("FCST_PPLTN_MAX")
                private Integer maximumForecastPopulation;
            }
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        static class RoadTrafficStatus {
            @JsonProperty("AVG_ROAD_DATA")
            private AvgRoadData averageRoadData;
            @JsonProperty("ROAD_TRAFFIC_STTS")
            private List<RoadTrafficStatus2> roadTrafficStatus;

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            static class AvgRoadData {
                @JsonProperty("ROAD_MSG")
                private String roadMessage;
                @JsonProperty("ROAD_TRAFFIC_IDX")
                private String roadTrafficIndex;
                @JsonProperty("ROAD_TRFFIC_TIME")
                private String roadTrafficTime;
                @JsonProperty("ROAD_TRAFFIC_SPD")
                private Long roadTrafficSpeed;
            }

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            static class RoadTrafficStatus2 {
                @JsonProperty("LINK_ID")
                private String LINK_ID;
                @JsonProperty("ROAD_NM")
                private String ROAD_NM;
                @JsonProperty("START_ND_CD")
                private String START_ND_CD;
                @JsonProperty("START_ND_NM")
                private String START_ND_NM;
                @JsonProperty("START_ND_XY")
                private String START_ND_XY;
                @JsonProperty("END_ND_CD")
                private String END_ND_CD;
                @JsonProperty("END_ND_NM")
                private String END_ND_NM;
                @JsonProperty("END_ND_XY")
                private String END_ND_XY;
                @JsonProperty("DIST")
                private String DIST;
                @JsonProperty("SPD")
                private String SPD;
                @JsonProperty("IDX")
                private String IDX;
                @JsonProperty("XYLIST")
                private String XYLIST;
            }
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ParkingLot {
            @JsonProperty("PRK_NM")
            private String name;
            @JsonProperty("PRK_CD")
            private String code;
            @JsonProperty("CPCITY")
            private Integer capacity;
            @JsonProperty("CUR_PRK_CNT")
            private Integer currentParkingCount;
            @JsonProperty("CUR_PRK_TIME")
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            private LocalDateTime currentParkingTime;
            @JsonProperty("CUR_PRK_YN")
            private String currentParkingYn;
            @JsonProperty("PAY_YN")
            private String payYn;
            @JsonProperty("RATES")
            private String rates;
            @JsonProperty("TIME_RATES")
            private String timeRates;
            @JsonProperty("ADD_RATES")
            private String addRates;
            @JsonProperty("ADD_TIME_RATES")
            private String addTimeRates;
            @JsonProperty("ADDRESS")
            private String address;
            @JsonProperty("ROAD_ADDR")
            private String roadAddr;
            @JsonProperty("LNG")
            private String longitude;
            @JsonProperty("LAT")
            private String latitude;
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        static class Sub {
            @JsonProperty("SUB_STN_NM")
            private String substationName;
            @JsonProperty("SUB_STN_LINE")
            private String substationLine;
            @JsonProperty("SUB_STN_RADDR")
            private String SUB_STN_RADDR;
            @JsonProperty("SUB_STN_JIBUN")
            private String SUB_STN_JIBUN;
            @JsonProperty("SUB_STN_X")
            private String SUB_STN_X;
            @JsonProperty("SUB_STN_Y")
            private String SUB_STN_Y;
            @JsonProperty("SUB_DETAIL")
            private List<SubDetail> SUB_DETAIL;

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            static class SubDetail {
                @JsonProperty("SUB_NT_STN")
                private String SUB_NT_STN;
                @JsonProperty("SUB_BF_STN")
                private String SUB_BF_STN;
                @JsonProperty("SUB_ROUTE_NM")
                private String SUB_ROUTE_NM;
                @JsonProperty("SUB_LINE")
                private String SUB_LINE;
                @JsonProperty("SUB_ORD")
                private String SUB_ORD;
                @JsonProperty("SUB_DIR")
                private String SUB_DIR;
                @JsonProperty("SUB_TERMINAL")
                private String SUB_TERMINAL;
                @JsonProperty("SUB_ARVTIME")
                private String SUB_ARVTIME;
                @JsonProperty("SUB_ARMG1")
                private String SUB_ARMG1;
                @JsonProperty("SUB_ARMG2")
                private String SUB_ARMG2;
                @JsonProperty("SUB_ARVINFO")
                private String SUB_ARVINFO;
            }
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        static class Bus {
            @JsonProperty("BUS_RESULT_MSG")
            private String BUS_RESULT_MSG;
            @JsonProperty("BUS_STN_ID")
            private String BUS_STN_ID;
            @JsonProperty("BUS_ARS_ID")
            private String BUS_ARS_ID;
            @JsonProperty("BUS_STN_NM")
            private String BUS_STN_NM;
            @JsonProperty("BUS_STN_X")
            private String BUS_STN_X;
            @JsonProperty("BUS_STN_Y")
            private String BUS_STN_Y;
            @JsonProperty("BUS_DETAIL")
            private List<BusDetail> BUS_DETAIL;

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            static class BusDetail {
                @JsonProperty("RTE_STN_NM")
                private String RTE_STN_NM;
                @JsonProperty("RTE_NM")
                private String RTE_NM;
                @JsonProperty("RTE_ID")
                private String RTE_ID;
                @JsonProperty("RTE_SECT")
                private String RTE_SECT;
                @JsonProperty("RTE_CONGEST_1")
                private String RTE_CONGEST_1;
                @JsonProperty("RTE_ARRV_TM_1")
                private String RTE_ARRV_TM_1;
                @JsonProperty("RTE_ARRV_STN_1")
                private String RTE_ARRV_STN_1;
                @JsonProperty("RTE_CONGEST_2")
                private String RTE_CONGEST_2;
                @JsonProperty("RTE_ARRV_TM_2")
                private String RTE_ARRV_TM_2;
                @JsonProperty("RTE_ARRV_STN_2")
                private String RTE_ARRV_STN_2;
            }
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class WeatherStatus {
            @JsonProperty("WEATHER_TIME")
            private String time;
            @JsonProperty("TEMP")
            private String temperature;
            @JsonProperty("SENSIBLE_TEMP")
            private String sensibleTemperature;
            @JsonProperty("MAX_TEMP")
            private String maximumTemperature;
            @JsonProperty("MIN_TEMP")
            private String minimumTemperature;
            @JsonProperty("HUMIDITY")
            private String humidity;
            @JsonProperty("PRECIPITATION")
            private String precipitation;
            @JsonProperty("PRECPT_TYPE")
            private String precipitationType;
            @JsonProperty("PCP_MSG")
            private String precipitationMessage;
            @JsonProperty("SUNRISE")
            private String SUNRISE;
            @JsonProperty("SUNSET")
            private String SUNSET;
            @JsonProperty("UV_INDEX_LVL")
            private String uvIndexLevel;
            @JsonProperty("UV_INDEX")
            private String uvIndex;
            @JsonProperty("UV_MSG")
            private String uvMessage;
            @JsonProperty("PM25_INDEX")
            private String pm25Index;
            @JsonProperty("PM25")
            private String pm25;
            @JsonProperty("PM10_INDEX")
            private String pm10Index;
            @JsonProperty("PM10")
            private String pm10;
            @JsonProperty("AIR_IDX")
            private String airIndex;
            @JsonProperty("AIR_IDX_MVL")
            private String airIndexMvl;
            @JsonProperty("AIR_IDX_MAIN")
            private String airIndexMain;
            @JsonProperty("AIR_MSG")
            private String airMessage;
            @JsonProperty("FCST24HOURS")
            private List<Forecast24Hour> weatherForecasts;
            @JsonProperty("NEWS_LIST")
            private List<Object> newsList;

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Forecast24Hour {
                @JsonProperty("FCST_DT")
                private String dayTime;
                @JsonProperty("TEMP")
                private String temperature24Hour;
                @JsonProperty("PRECIPITATION")
                private String precipitation;
                @JsonProperty("PRECPT_TYPE")
                private String precipitationType;
                @JsonProperty("RAIN_CHANCE")
                private String rainChance;
                @JsonProperty("SKY_STTS")
                private String skyStatus;
            }
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ChargerStation {
            @JsonProperty("STAT_NM")
            private String name;
            @JsonProperty("STAT_ID")
            private String stationId;
            @JsonProperty("STAT_ADDR")
            private String address;
            @JsonProperty("STAT_X")
            private String longitude;
            @JsonProperty("STAT_Y")
            private String latitude;
            @JsonProperty("STAT_USETIME")
            private String useTime;
            @JsonProperty("STAT_PARKPAY")
            private String parkPay;
            @JsonProperty("STAT_LIMITYN")
            private String limitYn;
            @JsonProperty("STAT_LIMITDETAIL")
            private String limitDetail;
            @JsonProperty("STAT_KINDDETAIL")
            private String kindDetail; // 충전소 장소 유형 (ex. 사업장, 공영주차장)
            @JsonProperty("CHARGER_DETAIL")
            private List<ChargerDetail> chargerDetails;

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class ChargerDetail {
                @JsonProperty("CHARGER_ID")
                private String chargerId;
                @JsonProperty("CHARGER_TYPE")
                private String type;
                @JsonProperty("CHARGER_STAT")
                private String status;
                @JsonProperty("LASTTSDT")
                private String LASTTSDT;
                @JsonProperty("LASTTEDT")
                private String LASTTEDT;
                @JsonProperty("NOWTSDT")
                private String NOWTSDT;
                @JsonProperty("OUTPUT")
                private String OUTPUT;
                @JsonProperty("METHOD")
                private String METHOD;
            }
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class AdjacentEvent {
            @JsonProperty("EVENT_NM")
            private String name;
            @JsonProperty("EVENT_PERIOD")
            private String period;
            @JsonProperty("EVENT_PLACE")
            private String place;
            @JsonProperty("EVENT_X")
            private String longitude;
            @JsonProperty("EVENT_Y")
            private String latitude;
            @JsonProperty("PAY_YN")
            private String payYn;
            @JsonProperty("THUMBNAIL")
            private String thumbnail;
            @JsonProperty("URL")
            private String url;
            @JsonProperty("EVENT_ETC_DETAIL")
            private String etcDetail;
        }
    }
}





