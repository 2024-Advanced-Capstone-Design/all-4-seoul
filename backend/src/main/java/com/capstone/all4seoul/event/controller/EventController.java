package com.capstone.all4seoul.event.controller;

import com.capstone.all4seoul.event.dto.response.DetailEventResponse;
import com.capstone.all4seoul.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    /**
     * 이벤트 단건 조회
     */
    @GetMapping("/{eventId}")
    public DetailEventResponse listDetailEvent(@PathVariable Long eventId) {
        return eventService.findById(eventId);
    }

    /**
     * 이벤트 전체 조회
     */
    @GetMapping("")
    public List<DetailEventResponse> listAllEvents() {
        return eventService.findAll();
    }

    /**
     * 특정 기간 내 이벤트 목록 조회
     */
    @GetMapping("/period")
    public List<DetailEventResponse> listEventsByPeriod(
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime
    ) {
        return eventService.findEventsByPeriod(startDateTime, endDateTime);
    }

    /**
     * 특정 좌표 기준 반경 내 이벤트 목록 조회
     */
    @GetMapping("/location")
    public List<DetailEventResponse> listEventsByLocation(
            @RequestParam Double x,
            @RequestParam Double y,
            @RequestParam int radius
    ) {
        return eventService.findEventsByLocation(x, y, radius);
    }
}
