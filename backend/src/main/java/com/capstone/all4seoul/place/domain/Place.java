package com.capstone.all4seoul.place.domain;

import com.capstone.all4seoul.bookmark.domain.Bookmark;
import com.capstone.all4seoul.event.domain.Event;
import com.capstone.all4seoul.review.domain.Review;
import com.capstone.all4seoul.seoulCityData.domain.MajorPlace;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Table(name = "places")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "website_uri")
    private String websiteUri;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "rating")
    private double rating;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Bookmark> bookmarks = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "major_place_id")
    private MajorPlace majorPlace;

    public static Place createPlace(
            List<Event> events,
            String name,
            List<Review> reviews,
            String phoneNumber,
            String address,
            Double x,
            Double y,
            Category category,
//            double rating,
            MajorPlace majorPlace
    ) {
        Place place = new Place();

        place.events = events;
        place.name = name;
        place.reviews = reviews;
        place.phoneNumber = phoneNumber;
        place.address = address;
        place.x = x;
        place.y = y;
        place.websiteUri = null;
        place.category = category;
//        place.rating = rating;
        place.majorPlace = majorPlace;

        return place;
    }
}
