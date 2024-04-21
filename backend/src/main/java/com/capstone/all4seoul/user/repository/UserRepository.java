package com.capstone.all4seoul.user.repository;

import com.capstone.all4seoul.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Spring Data JPA
     * save(), delete() 는 자동구현 되어있음
     */

    //로그인 아이디로 조회
    User findByLoginId(String loginId);

    //컬렉션 조회
    List<User> findListByUsername(String username);

    //단건 조회
    User findFirstByUsername(String username);
}
