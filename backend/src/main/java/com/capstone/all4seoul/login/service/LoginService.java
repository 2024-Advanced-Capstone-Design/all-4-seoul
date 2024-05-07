package com.capstone.all4seoul.login.service;

import com.capstone.all4seoul.user.domain.User;
import com.capstone.all4seoul.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public User login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId).get();
        if (user != null && user.getLoginPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

