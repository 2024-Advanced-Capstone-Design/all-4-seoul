package com.capstone.all4seoul.user.controller;

import com.capstone.all4seoul.bookmark.dto.request.DeleteBookmarkRequest;
import com.capstone.all4seoul.bookmark.dto.request.FindBookmarkedPlacesRequest;
import com.capstone.all4seoul.place.dto.response.DetailPlaceResponse;
import com.capstone.all4seoul.user.domain.User;
import com.capstone.all4seoul.user.dto.request.JoinUserRequest;
import com.capstone.all4seoul.user.dto.request.UpdateUserRequest;
import com.capstone.all4seoul.user.dto.response.DetailUserResponse;
import com.capstone.all4seoul.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입
     */
    @PostMapping("/users")
    public ResponseEntity<Object> join(@RequestBody JoinUserRequest request) {
        Long userId = userService.join(request);

        return ResponseEntity.created(URI.create("users/" + userId)).build();
    }

    @GetMapping("/user-info")
    public ResponseEntity<DetailUserResponse> getUserInfo(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findById(userId);
        if (user == null) {
            // 사용자 정보가 조회되지 않은 경우
            return ResponseEntity.notFound().build();
        }

        // 조회된 사용자 정보를 적절한 형식으로 변환하여 클라이언트에 반환
        DetailUserResponse response = DetailUserResponse.of(user);

        return ResponseEntity.ok(response);
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
    @GetMapping("/users")
    public List<DetailUserResponse> findAll() {

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
     * 북마크 추가
     */
    @PostMapping("/users/bookmarks")
    public ResponseEntity<Void> addBookmark(@RequestBody DeleteBookmarkRequest request) {
        Long bookmarkId = userService.addBookmark(request);

        return ResponseEntity.created(URI.create("users/bookmarks/" + bookmarkId)).build();
    }

    /**
     * 북마크한 장소 목록 조회
     */
    @GetMapping("/users/bookmarks")
    public List<DetailPlaceResponse> findBookmarkedPlaces(@RequestBody FindBookmarkedPlacesRequest request) {
        User user = userService.findById(request.getUserId());

        return userService.findBookmarkedPlaces(user);
    }

    /**
     * 북마크 제거
     */
    @DeleteMapping("/users/bookmarks")
    public void deleteBookmark(@RequestBody DeleteBookmarkRequest request) {
        userService.deleteBookmark(request);
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
