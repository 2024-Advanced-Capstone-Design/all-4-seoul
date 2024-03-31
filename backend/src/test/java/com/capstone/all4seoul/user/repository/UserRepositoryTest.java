package com.capstone.all4seoul.user.repository;

import com.capstone.all4seoul.user.domain.Gender;
import com.capstone.all4seoul.user.domain.Mbti;
import com.capstone.all4seoul.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void initData() {
        User user = new User();
        user.setLoginId("testUser");
        user.setLoginPassword("test");
        user.setUsername("kim");
        user.setBirth(LocalDate.now());
        user.setMbti(Mbti.ISTJ);
        user.setGender(Gender.Male);
        user.setNickName("자기야");
        user.setCredit(0);
        userRepository.save(user);

        User user1 = new User();
        user1.setLoginId("testUser2");
        user1.setLoginPassword("test2");
        user1.setUsername("park");
        user1.setBirth(LocalDate.now());
        user1.setMbti(Mbti.ISTJ);
        user1.setGender(Gender.Male);
        user1.setNickName("자기야2");
        user1.setCredit(0);
        userRepository.save(user1);

        // user1 user2 : username 동일하게
        User user2 = new User();
        user2.setLoginId("testUser3");
        user2.setLoginPassword("test3");
        user2.setUsername("park");
        user2.setBirth(LocalDate.now());
        user2.setMbti(Mbti.ISTJ);
        user2.setGender(Gender.Male);
        user2.setNickName("자기야3");
        user2.setCredit(0);
        userRepository.save(user2);
    }

    //아이디로 유저 조회
    @Test
    void findByLoginId() {
        User findUser = userRepository.findByLoginId("testUser");
        assertThat(findUser.getUsername()).isEqualTo("kim");

        User findUser2 = userRepository.findByLoginId("testUser2");
        assertThat(findUser2.getUsername()).isEqualTo("park");
    }

    //이름으로 유저 단건 조회 (동명이인 존재시 첫번째 유저 조회)
    @Test
    void findUserByUsername() {
        User findUser = userRepository.findFirstByUsername("park");
        assertThat(findUser.getLoginId()).isEqualTo("testUser2");
    }

    //이름으로 유저 리스트 조회 (동명이인)
    @Test
    void findAllByUsername() {
        List<User> users = userRepository.findListByUsername("park");
        assertThat(users.get(0).getLoginId()).isEqualTo("testUser2");
        assertThat(users.get(1).getLoginId()).isEqualTo("testUser3");
    }
}