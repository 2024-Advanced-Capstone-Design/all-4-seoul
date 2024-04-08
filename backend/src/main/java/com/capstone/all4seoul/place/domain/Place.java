package com.capstone.all4seoul.place.domain;

import com.capstone.all4seoul.event.domain.Event;
import com.capstone.all4seoul.review.domain.Review;
import com.capstone.all4seoul.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "places")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE") //하위 테이블 구분 칼럼
@DiscriminatorValue("place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Event> events;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "phone_number", nullable = false, unique = true, length = 11)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;



    /**
     * 연관관계 메서드
     */
    public void setUser(User user) {
        this.user = user;
        user.getPlaces().add(this);
    }
}
