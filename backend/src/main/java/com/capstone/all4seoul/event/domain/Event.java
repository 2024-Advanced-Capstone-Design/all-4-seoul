package com.capstone.all4seoul.event.domain;

import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.review.domain.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public static Event createEvent(
            String name,
            LocalDateTime startDate,
            LocalDateTime endDate,
            int price,
            Place place,
            Double x,
            Double y
    ) {
        Event event = new Event();

        event.name = name;
        event.startDate = startDate;
        event.endDate = endDate;
        event.price = price;
        event.place = place;
        event.x = x;
        event.y = y;

        return event;
    }
}
