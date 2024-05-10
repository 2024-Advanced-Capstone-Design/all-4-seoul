package com.capstone.all4seoul.login.controller;

import com.capstone.all4seoul.login.dto.request.LoginRequest;
import com.capstone.all4seoul.login.service.LoginService;
import com.capstone.all4seoul.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        User user = loginService.login(loginRequest.getLoginId(), loginRequest.getLoginPassword());
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            HttpCookie cookie = ResponseCookie.from("sessionId", session.getId())
                    .httpOnly(true) //클라이언트 스크립트에서 쿠키 설정 불가능
                    .path("/") //모든 경로에 접근 가능
                    .maxAge(1800) // 30분
                    .build();
            return ResponseEntity.ok()
                    .header("Set-Cookie", cookie.toString()) //쿠키를 응답헤더에 추가
                    .body("로그인 성공");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
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
