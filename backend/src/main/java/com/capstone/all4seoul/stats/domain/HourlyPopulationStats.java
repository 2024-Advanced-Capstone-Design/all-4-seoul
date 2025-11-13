package com.capstone.all4seoul.stats.domain;

import com.capstone.all4seoul.stats.dto.PopulationStatsDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "hourly_population_stats")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HourlyPopulationStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaCode;
    private LocalDate statDate;
    private int statHour;

    private Double avgMinPopulation;
    private Double avgMaxPopulation;

    private Double avgPopRate0010;
    private Double avgPopRate2030;
    private Double avgPopRate4050;
    private Double avgPopRate6070;

    public HourlyPopulationStats(PopulationStatsDto dto, LocalDate date) {
        this.areaCode = dto.getAreaCode();
        this.statDate = date;
        this.statHour = dto.getHour();
        this.avgMinPopulation = dto.getAvgMinPopulation();
        this.avgMaxPopulation = dto.getAvgMaxPopulation();
        this.avgPopRate0010 = dto.getAvgPopRate0010();
        this.avgPopRate2030 = dto.getAvgPopRate2030();
        this.avgPopRate4050 = dto.getAvgPopRate4050();
        this.avgPopRate6070 = dto.getAvgPopRate6070();
    }
}