package com.capstone.all4seoul.event.repository;

import com.capstone.all4seoul.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByName(String name);

    @Query("SELECT e FROM Event e WHERE e.startDateTime >= :startDateTime AND e.endDateTime <= :endDateTime")
    List<Event> findByPeriod(@Param("start") LocalDateTime startDateTime, @Param("end") LocalDateTime endDateTime);

    void deleteById(Long eventId);
}
