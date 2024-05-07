package com.capstone.all4seoul.login.controller;

import com.capstone.all4seoul.login.dto.request.LoginRequest;
import com.capstone.all4seoul.login.service.LoginService;
import com.capstone.all4seoul.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            return ResponseEntity.ok("로그인 성공");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }

}
