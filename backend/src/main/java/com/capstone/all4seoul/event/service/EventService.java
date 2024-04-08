package com.capstone.all4seoul.event.service;

import com.capstone.all4seoul.event.dto.response.DetailEventResponse;
import com.capstone.all4seoul.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    /**
     * 이벤트 단건 조회
     */
    public DetailEventResponse findById(Long eventId) {
        return DetailEventResponse.of(eventRepository.findById(eventId).get());
    }

    /**
     * 이벤트 전체 조회
     */
    public List<DetailEventResponse> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(DetailEventResponse::of)
                .toList();
    }

    /**
     * 특정 기간 내의 이벤트 목록 조회
     */
    public List<DetailEventResponse> findEventsByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return eventRepository.findByPeriod(startDateTime, endDateTime)
                .stream()
                .map(DetailEventResponse::of)
                .toList();
    }

    /**
     * 반경 안의 이벤트 목록 조회
     */
    private double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {

        final int EARTH_RADIUS = 6371; // 지구의 반지름 (km)

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c * 1000;
    }
}