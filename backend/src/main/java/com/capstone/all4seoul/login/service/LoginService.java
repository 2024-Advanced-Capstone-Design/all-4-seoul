package com.capstone.all4seoul.login.service;

import com.capstone.all4seoul.user.domain.User;
import com.capstone.all4seoul.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public User login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(() -> new EntityNotFoundException("유저 정보가 없음"));
        if (user != null && user.getLoginPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

