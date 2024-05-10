package com.capstone.all4seoul.login.controller;

import com.capstone.all4seoul.login.dto.request.LoginRequest;
import com.capstone.all4seoul.login.service.LoginService;
import com.capstone.all4seoul.user.domain.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {

        User loginUser = loginService.login(loginRequest.getLoginId(), loginRequest.getLoginPassword());
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
        HttpSession session = request.getSession(true);

        session.setAttribute("loginUser", loginUser.getId());
        session.setMaxInactiveInterval(1800);

//        Cookie sessionCookie = new Cookie("theCookieMadeByMe", session.getId());
//        sessionCookie.setMaxAge(1800); // 세션 만료 시간 설정 (초 단위)
//        sessionCookie.setPath("/"); // 쿠키의 경로 설정

//        response.addCookie(sessionCookie);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("로그인 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return ResponseEntity.ok("로그아웃 성공");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그아웃 실패");
    }
}
