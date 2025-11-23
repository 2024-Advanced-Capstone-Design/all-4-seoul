package com.capstone.all4seoul.seoulCityData.service;

import com.capstone.all4seoul.seoulCityData.repository.LivePopulationStatusRepository;
import com.capstone.all4seoul.seoulCityData.repository.ParkingLotRepository;
import com.capstone.all4seoul.stats.domain.HourlyParkingStats;
import com.capstone.all4seoul.stats.domain.HourlyPopulationStats;
import com.capstone.all4seoul.stats.dto.ParkingStatsDto;
import com.capstone.all4seoul.stats.dto.PopulationStatsDto;
import com.capstone.all4seoul.stats.repository.HourlyParkingStatsRepository;
import com.capstone.all4seoul.stats.repository.HourlyPopulationStatsRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataRollupService {

    private final HourlyPopulationStatsRepository hourlyPopulationStatsRepository;
    private final HourlyParkingStatsRepository hourlyParkingStatsRepository;
    private final LivePopulationStatusRepository livePopulationStatusRepository;
    private final ParkingLotRepository parkingLotRepository;

    @Retryable(retryFor = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void runDailyStatisticsRollup(LocalDate yesterday) {
        LocalDateTime startOfYesterday = yesterday.atStartOfDay(); // 어제 00:00:00
        LocalDateTime endOfYesterday = yesterday.plusDays(1).atStartOfDay(); // 오늘 00:00:00

        log.info("[BATCH-LOGIC] Statistics Rollup for date: {}", yesterday);

        // --- 1. 인구 통계 롤업 ---
        try {
            List<PopulationStatsDto> popDtos = livePopulationStatusRepository.getHourlyStats(startOfYesterday, endOfYesterday);
            List<HourlyPopulationStats> popStats = popDtos.stream()
                    .map(dto -> new HourlyPopulationStats(dto, yesterday))
                    .collect(Collectors.toList());
            hourlyPopulationStatsRepository.saveAll(popStats);
            log.info("[BATCH-LOGIC] Saved {} HourlyPopulationStats", popStats.size());

        } catch (Exception e) {
            log.error("[BATCH-FAIL] Failed to rollup Population Stats. Error: {}", e.getMessage());
            throw new RuntimeException("Population Stats Rollup Failed", e); // 실패 시 Batch에 알림
        }

        // --- 2. 주차장 통계 롤업 ---
        try {
            List<ParkingStatsDto> parkingDtos = parkingLotRepository.getHourlyStats(startOfYesterday, endOfYesterday);
            List<HourlyParkingStats> parkingStats = parkingDtos.stream()
                    .map(dto -> new HourlyParkingStats(dto, yesterday))
                    .collect(Collectors.toList());
            hourlyParkingStatsRepository.saveAll(parkingStats);
            log.info("[BATCH-LOGIC] Saved {} HourlyParkingStats", parkingStats.size());
        } catch (Exception e) {
            log.error("[BATCH-FAIL] Failed to rollup Parking Stats. Error: {}", e.getMessage());
            throw new RuntimeException("Parking Stats Rollup Failed", e); // 실패 시 Batch에 알림
        }
    }

    @Recover
    public void recover(Exception e) {
        log.error("[BATCH-FAIL] Total 3 Failure for rollup Population Stats. Error: {}", e.getMessage());
    }
}