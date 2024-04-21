package com.capstone.all4seoul.bookmark.domain;

import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "bookmarks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "bookmark_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false, updatable = false)
    private Place place;

    public static Bookmark createBookmark(User user, Place place) {
        Bookmark bookmark = new Bookmark();

        bookmark.setUser(user);
        bookmark.setPlace(place);

        return bookmark;
    }

    /**
     * 연관관계 편의 메소드
     */
    public void setUser(User user) {
        this.user = user;
        user.getBookmarks().addBookmark(this);
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public boolean isBookmarkOf(Place place) {
        return this.place.getId().equals(place.getId());
    }

    public boolean isBookmarkOf(User user) {
        return this.user.getId().equals(user.getId());
    }

    public Long getPlaceId() {
        return this.place.getId();
    }
}
