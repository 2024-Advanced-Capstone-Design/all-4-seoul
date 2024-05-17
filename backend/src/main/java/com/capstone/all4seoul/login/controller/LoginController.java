package com.capstone.all4seoul.login.controller;

import com.capstone.all4seoul.login.dto.request.LoginRequest;
import com.capstone.all4seoul.login.service.LoginService;
import com.capstone.all4seoul.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        session.setAttribute("userId", loginUser.getId());
        session.setMaxInactiveInterval(1800);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("로그인 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();

            HttpCookie cookie = ResponseCookie.from("sessionId", "")
                    .path("/")
                    .maxAge(0)
                    .build();

            //쿠키를 HTTP 응답에 추가해서 클라이언트에 반환
            response.addHeader("Set-Cookie", cookie.toString());

            return ResponseEntity.ok("로그아웃 성공");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그아웃 실패");
    }
}
