package com.capstone.all4seoul.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PopulationStatsDto {
    private String areaCode;
    private int hour;
    private Double avgMinPopulation;
    private Double avgMaxPopulation;
    private Double avgPopRate0010;
    private Double avgPopRate2030;
    private Double avgPopRate4050;
    private Double avgPopRate6070;
}