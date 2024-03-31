package com.capstone.all4seoul.event.repository;

import com.capstone.all4seoul.event.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAll(Pageable pageable);

    Event findByName(String name);

    void deleteById(Long eventId);
}
