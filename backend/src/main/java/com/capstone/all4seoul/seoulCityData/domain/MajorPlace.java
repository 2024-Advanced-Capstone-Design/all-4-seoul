package com.capstone.all4seoul.seoulCityData.domain;

import com.capstone.all4seoul.common.domain.BaseTimeEntity;
import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.seoulCityData.domain.charger.ChargerStation;
import com.capstone.all4seoul.seoulCityData.domain.event.AdjacentEvent;
import com.capstone.all4seoul.seoulCityData.domain.parkingLot.ParkingLot;
import com.capstone.all4seoul.seoulCityData.domain.population.LivePopulationStatus;
import com.capstone.all4seoul.seoulCityData.domain.weather.WeatherStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Table(name = "major_places")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class MajorPlace extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_place_id", nullable = false)
    private Long id;

    private String areaName;

    private String areaCode;

    @OneToMany(mappedBy = "majorPlace", cascade = CascadeType.ALL)
    private List<LivePopulationStatus> livePopulationStatuses = new ArrayList<>();

    @OneToMany(mappedBy = "majorPlace", cascade = CascadeType.ALL)
    private List<ParkingLot> parkingLots = new ArrayList<>();

    @OneToMany(mappedBy = "majorPlace", cascade = CascadeType.ALL)
    private List<ChargerStation> chargerStations = new ArrayList<>();

    @OneToMany(mappedBy = "majorPlace", cascade = CascadeType.ALL)
    private List<WeatherStatus> weatherStatuses = new ArrayList<>();

    @OneToMany(mappedBy = "majorPlace", cascade = CascadeType.ALL)
    private List<AdjacentEvent> adjacentEvents = new ArrayList<>();

    @OneToOne(mappedBy = "majorPlace")
    private Place place;

    @Column(nullable = false)
    private boolean latest = true;
  
    public MajorPlace (
            String areaName,
            String areaCode,
            List<LivePopulationStatus> livePopulationStatuses,
            List<ParkingLot> parkingLots,
            List<ChargerStation> chargerStations,
            List<WeatherStatus> weatherStatuses,
            List<AdjacentEvent> adjacentEvents
    ) {
        this.areaName = areaName;
        this.areaCode = areaCode;
        setLivePopulationStatuses(livePopulationStatuses);
        setParkingLots(parkingLots);
        setChargerStations(chargerStations);
        setWeatherStatuses(weatherStatuses);
        setAdjacentEvents(adjacentEvents);
    }

    private void setLivePopulationStatuses(List<LivePopulationStatus> livePopulationStatuses) {
        livePopulationStatuses.stream()
                .forEach(livePopulationStatus -> livePopulationStatus.setMajorPlace(this));
    }

    private void setParkingLots(List<ParkingLot> parkingLots) {
        parkingLots.stream()
                .forEach(parkingLot -> parkingLot.setMajorPlace(this));
    }

    private void setChargerStations(List<ChargerStation> chargerStations) {
        chargerStations.stream()
                .forEach(chargerStation -> chargerStation.setMajorPlace(this));
    }

    private void setWeatherStatuses(List<WeatherStatus> weatherStatuses) {
        weatherStatuses.stream()
                .forEach(weatherStatus -> weatherStatus.setMajorPlace(this));
    }

    private void setAdjacentEvents(List<AdjacentEvent> adjacentEvents) {
        adjacentEvents.stream()
                .forEach(adjacentEvent -> adjacentEvent.setMajorPlace(this));
    }
}
