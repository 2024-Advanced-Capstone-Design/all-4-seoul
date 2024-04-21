package com.capstone.all4seoul.user.domain;

import com.capstone.all4seoul.bookmark.domain.Bookmarks;
import com.capstone.all4seoul.review.domain.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true, updatable = false, length = 30)
    private String loginId;

    @Column(name = "login_password", nullable = false, length = 100)
    private String loginPassword;

    @Column(name = "name", nullable = false, length = 30)
    private String username;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "mbti", nullable = false)
    private Mbti mbti;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "nickname", nullable = false, unique = true, length = 30)
    private String nickname;

    @Column(name = "credit")
    private int credit;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    @Embedded
    private Bookmarks bookmarks = Bookmarks.createEmptyBookmarks();
  
    public static User createUser(
            String loginId,
            String loginPassword,
            String username,
            LocalDate birth,
            Mbti mbti,
            Gender gender,
            String nickname) {
        User user = new User();

        user.loginId = loginId;
        user.loginPassword = loginPassword;
        user.username = username;
        user.birth = birth;
        user.mbti = mbti;
        user.gender = gender;
        user.nickname = nickname;
        user.credit = 0;

        return user;
    }

    /**
     * 사용자 수정 관련 메서드
     */
    public void updateBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void updateMbti(Mbti mbti) {
        this.mbti = mbti;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 사용자 북마크 제거 메서드
     */
    public void deleteBookmark(Long placeId) {
        bookmarks.deleteBookmark(placeId);
    }
}
