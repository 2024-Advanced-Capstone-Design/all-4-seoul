package com.capstone.all4seoul.stats.repository;

import com.capstone.all4seoul.stats.domain.HourlyPopulationStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourlyPopulationStatsRepository extends JpaRepository<HourlyPopulationStats, Long> {
}