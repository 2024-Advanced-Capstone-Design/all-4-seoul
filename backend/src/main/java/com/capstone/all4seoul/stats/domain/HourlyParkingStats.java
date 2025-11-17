package com.capstone.all4seoul.stats.domain;

import com.capstone.all4seoul.stats.dto.ParkingStatsDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "hourly_parking_stats")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HourlyParkingStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String areaCode;

    @Column(nullable = false)
    private LocalDate statDate;

    @Column(nullable = false)
    private Integer statHour;

    private Double avgCapacity;
    private Double avgCurrentParkingCount;

    public HourlyParkingStats(ParkingStatsDto dto, LocalDate date) {
        this.areaCode = dto.getAreaCode();
        this.statDate = date;
        this.statHour = dto.getHour();
        this.avgCapacity = dto.getAvgCapacity();
        this.avgCurrentParkingCount = dto.getAvgCurrentParkingCount();
    }
}