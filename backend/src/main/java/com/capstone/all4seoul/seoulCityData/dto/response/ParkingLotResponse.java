package com.capstone.all4seoul.seoulCityData.dto.response;

import com.capstone.all4seoul.seoulCityData.domain.parkingLot.ParkingLot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParkingLotResponse {
    private Long id;
    private String name;
    private String code;
    private String capacity;
    private String currentParkingCount;
    private String currentParkingTime;
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

    public static ParkingLotResponse of(ParkingLot parkingLot) {
        ParkingLotResponse parkingLotResponse = new ParkingLotResponse();

        parkingLotResponse.id = parkingLot.getId();
        parkingLotResponse.name = parkingLot.getName();
        parkingLotResponse.code = parkingLot.getCode();
        parkingLotResponse.capacity = parkingLot.getCapacity();
        parkingLotResponse.currentParkingCount = parkingLot.getCurrentParkingCount();
        parkingLotResponse.currentParkingTime = parkingLot.getCurrentParkingTime();
        parkingLotResponse.currentParkingYn = parkingLot.getCurrentParkingYn();
        parkingLotResponse.payYn = parkingLot.getPayYn();
        parkingLotResponse.rates = parkingLot.getRates();
        parkingLotResponse.timeRates = parkingLot.getTimeRates();
        parkingLotResponse.addRates = parkingLot.getAddRates();
        parkingLotResponse.addTimeRates = parkingLot.getAddTimeRates();
        parkingLotResponse.address = parkingLot.getAddress();
        parkingLotResponse.roadAddr = parkingLot.getRoadAddr();
        parkingLotResponse.longitude = parkingLot.getLongitude();
        parkingLotResponse.latitude = parkingLot.getLatitude();

        return parkingLotResponse;
    }
}
