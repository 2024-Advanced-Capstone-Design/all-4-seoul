package com.capstone.all4seoul.event.repository;

import com.capstone.all4seoul.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByName(String name);

    void deleteById(Long eventId);
}
