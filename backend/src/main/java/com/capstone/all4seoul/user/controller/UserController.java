package com.capstone.all4seoul.user.controller;

import com.capstone.all4seoul.user.domain.User;
import com.capstone.all4seoul.user.dto.request.JoinUserRequest;
import com.capstone.all4seoul.user.dto.request.UpdateUserRequest;
import com.capstone.all4seoul.user.dto.response.DetailUserResponse;
import com.capstone.all4seoul.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 회원가입
@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    /**
     * 회원 가입
     */
    @PostMapping("/users")
    public ResponseEntity<Object> join(@RequestBody JoinUserRequest request) {

        userService.join(request);

        return ResponseEntity.noContent().build();
    }

    /**
     * 사용자 단건 조회
     */
    @GetMapping("/users/{userId}")
    public DetailUserResponse listDetailUser(@PathVariable Long userId) {

        return DetailUserResponse.of(userService.findById(userId));
    }

    /**
     * 사용자 리스트 조회
     */
    @GetMapping("/user/find")
    public List<User> findAll() {

        return userService.findAll();
    }

    /**
     * 회원 수정
     */
    @PatchMapping("/users/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {

        userService.updateUser(userId, request);

        return ResponseEntity.noContent().build();
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {

        userService.deleteUser(userService.findById(userId));

        return ResponseEntity.noContent().build();
    }
}
