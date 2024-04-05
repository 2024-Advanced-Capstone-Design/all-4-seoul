package com.capstone.all4seoul.user.domain;

import com.capstone.all4seoul.place.domain.Place;
import com.capstone.all4seoul.review.domain.Review;
import com.capstone.all4seoul.user.dto.request.JoinUserRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
    private String nickName;

    @Column(name = "credit", nullable = false)
    private int credit;

    //    @OneToMany(mappedBy = "user")
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    // 북마크
//    @OneToMany(mappedBy = "user")
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Place> places = new ArrayList<>();

    public static User createUser(JoinUserRequest joinUserRequest) {
        User user = new User();
        user.setLoginId(joinUserRequest.getLoginId());
        user.setLoginPassword(joinUserRequest.getLoginPassword());
        user.setUsername(joinUserRequest.getUsername());
        user.setBirth(joinUserRequest.getBirth());
        user.setMbti(joinUserRequest.getMbti());
        user.setGender(joinUserRequest.getGender());
        user.setCredit(0);
        return user;
    }
}
