package com.capstone.all4seoul.stats.repository;

import com.capstone.all4seoul.stats.domain.HourlyParkingStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourlyParkingStatsRepository extends JpaRepository<HourlyParkingStats, Long> {
}