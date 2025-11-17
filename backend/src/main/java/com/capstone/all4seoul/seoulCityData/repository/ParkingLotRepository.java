package com.capstone.all4seoul.seoulCityData.repository;

import com.capstone.all4seoul.seoulCityData.domain.parkingLot.ParkingLot;
import com.capstone.all4seoul.stats.dto.ParkingStatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    /**
     * 어제자 원본 데이터를 기반으로 시간대별 주차장 통계를 롤업하는 쿼리
     */
    @Query("SELECT new com.capstone.all4seoul.stats.dto.ParkingStatsDto(" +
            "  mp.areaCode, " +
            "  FUNCTION('HOUR', mp.createdAt) as hour, " +
            "  AVG(p.capacity), " +
            "  AVG(p.currentParkingCount) " +
            ") " +
            "FROM ParkingLot p JOIN p.majorPlace mp " +
            "WHERE mp.latest = false AND mp.createdAt >= :start AND mp.createdAt < :end " +
            "GROUP BY mp.areaCode, FUNCTION('HOUR', mp.createdAt)")
    List<ParkingStatsDto> getHourlyStats(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}