package com.capstone.all4seoul.seoulCityData.repository;

import com.capstone.all4seoul.seoulCityData.domain.population.LivePopulationStatus;
import com.capstone.all4seoul.stats.dto.PopulationStatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface LivePopulationStatusRepository extends JpaRepository<LivePopulationStatus, Long> {

    @Query("SELECT new com.capstone.all4seoul.stats.dto.PopulationStatsDto(" +
            "  mp.areaCode, " +
            "  FUNCTION('HOUR', mp.createdAt) as hour, " +
            "  AVG(l.minimumAreaPopulation), " +
            "  AVG(l.maximumAreaPopulation), " +
            "  AVG(l.populationRate0 + l.populationRate10), " +
            "  AVG(l.populationRate20 + l.populationRate30), " +
            "  AVG(l.populationRate40 + l.populationRate50), " +
            "  AVG(l.populationRate60 + l.populationRate70) " +
            ") " +
            "FROM LivePopulationStatus l JOIN l.majorPlace mp " +
            // latest=false (어제 데이터)이며, 지정된 시간 범위 내
            "WHERE mp.latest = false AND mp.createdAt >= :start AND mp.createdAt < :end " +
            "GROUP BY mp.areaCode, FUNCTION('HOUR', mp.createdAt)") // 장소별, 시간별 그룹화
    List<PopulationStatsDto> getHourlyStats(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}