package com.capstone.all4seoul.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParkingStatsDto {
    private String areaCode;
    private int hour;
    private Double avgCapacity;
    private Double avgCurrentParkingCount;
}