package com.capstone.all4seoul.place.repository;

import com.capstone.all4seoul.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAll();
    List<Place> findByName(String name);
    void deleteById(Long placeId);
}
