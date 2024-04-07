package com.capstone.all4seoul.event.domain;

import com.capstone.all4seoul.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "period", nullable = false)
    private String period;

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
