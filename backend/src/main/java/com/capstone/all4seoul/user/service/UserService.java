package com.capstone.all4seoul.user.service;

import com.capstone.all4seoul.user.domain.User;
import com.capstone.all4seoul.user.dto.request.JoinUserRequest;
import com.capstone.all4seoul.user.dto.request.UpdateUserRequest;
import com.capstone.all4seoul.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    public void join(JoinUserRequest joinUserRequest) {
        User user = User.createUser(joinUserRequest);

        // 사용자 생성 전 중복 로그인 ID 확인
        if (userRepository.findByLoginId(joinUserRequest.getLoginId()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 로그인 아이디입니다.");
        }
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("사용자 생성 중 오류가 발생했습니다.", e);
        }
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    //로그인 아이디로 조회
    public User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    //컬렉션 조회
    public List<User> findListByUsername(String username) {
        return userRepository.findListByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUser(Long id, UpdateUserRequest updateUserRequest) {

        User findUser = userRepository.findById(id).get();

        findUser.updateBirth(updateUserRequest.getBirth());
        findUser.updateMbti(updateUserRequest.getMbti());
        findUser.updateNickname(updateUserRequest.getNickname());
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
