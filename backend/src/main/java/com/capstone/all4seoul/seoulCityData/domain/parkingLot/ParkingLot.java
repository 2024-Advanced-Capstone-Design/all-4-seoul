package com.capstone.all4seoul.seoulCityData.domain.parkingLot;

import com.capstone.all4seoul.place.dto.response.externalApi.PlaceSearchResponseBySeoulDataApi;
import com.capstone.all4seoul.seoulCityData.domain.MajorPlace;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "parking_lots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_lot_id")
    private Long id;

    private String name;

    private String code;

    private Integer capacity;

    private Integer currentParkingCount;

    private LocalDateTime currentParkingTime;

    private String currentParkingYn;

    private String payYn;

    private String rates;

    private String timeRates;

    private String addRates;

    private String addTimeRates;

    private String address;

    private String roadAddr;

    private String longitude;

    private String latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_place_id")
    private MajorPlace majorPlace;

    public static ParkingLot createParkingLot(
            PlaceSearchResponseBySeoulDataApi.CityData.ParkingLot fetchedParkingLot
    ) {
        ParkingLot parkingLot = new ParkingLot();

        parkingLot.name = fetchedParkingLot.getName();
        parkingLot.code = fetchedParkingLot.getCode();
        parkingLot.capacity = fetchedParkingLot.getCapacity();
        parkingLot.currentParkingCount = fetchedParkingLot.getCurrentParkingCount();
        parkingLot.currentParkingTime = fetchedParkingLot.getCurrentParkingTime();
        parkingLot.currentParkingYn = fetchedParkingLot.getCurrentParkingYn();
        parkingLot.payYn = fetchedParkingLot.getPayYn();
        parkingLot.rates = fetchedParkingLot.getRates();
        parkingLot.timeRates = fetchedParkingLot.getTimeRates();
        parkingLot.addRates = fetchedParkingLot.getAddRates();
        parkingLot.addTimeRates = fetchedParkingLot.getAddTimeRates();
        parkingLot.address = fetchedParkingLot.getAddress();
        parkingLot.roadAddr = fetchedParkingLot.getRoadAddr();
        parkingLot.longitude = fetchedParkingLot.getLongitude();
        parkingLot.latitude = fetchedParkingLot.getLatitude();

        return parkingLot;
    }

    public void setMajorPlace(MajorPlace majorPlace) {
        this.majorPlace = majorPlace;
        majorPlace.getParkingLots().add(this);
    }
}
