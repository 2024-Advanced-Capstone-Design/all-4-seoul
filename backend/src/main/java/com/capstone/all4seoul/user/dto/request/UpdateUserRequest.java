package com.capstone.all4seoul.user.dto.request;

import com.capstone.all4seoul.user.domain.Mbti;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private LocalDate birth;
    private Mbti mbti;
    private String nickName;
}
