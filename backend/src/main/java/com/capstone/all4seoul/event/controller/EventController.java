package com.capstone.all4seoul.event.controller;

import com.capstone.all4seoul.event.dto.response.DetailEventResponse;
import com.capstone.all4seoul.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * 이벤트 단건 조회
     */
    @GetMapping("/events/{eventId}")
    public DetailEventResponse listDetailEvent(@PathVariable Long eventId) {
        return eventService.findById(eventId);
    }

    /**
     * 이벤트 전체 조회
     */
    @GetMapping("/events")
    public List<DetailEventResponse> listAllEvents() {
        return eventService.findAll();
    }
}
