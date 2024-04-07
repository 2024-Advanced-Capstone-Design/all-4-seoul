package com.capstone.all4seoul.event.domain;

import com.capstone.all4seoul.place.domain.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String price;

    @OneToOne
    @JoinColumn(name = "place_id")
    private Place location;

    @Column(name = "x", nullable = false, length = 30)
    private String x;

    @Column(name = "y", nullable = false, length = 30)
    private String y;
}
