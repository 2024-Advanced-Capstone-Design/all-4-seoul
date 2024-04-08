package com.capstone.all4seoul.event.service;

import com.capstone.all4seoul.event.dto.response.DetailEventResponse;
import com.capstone.all4seoul.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}