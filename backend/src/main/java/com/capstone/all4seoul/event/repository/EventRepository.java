package com.capstone.all4seoul.event.repository;

import com.capstone.all4seoul.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByName(String name);

    @Query("SELECT e FROM Event e WHERE e.startDate >= :start AND e.endDate <= :end")
    List<Event> findByPeriod(LocalDateTime startDate, LocalDateTime endDate);

    void deleteById(Long eventId);
}
