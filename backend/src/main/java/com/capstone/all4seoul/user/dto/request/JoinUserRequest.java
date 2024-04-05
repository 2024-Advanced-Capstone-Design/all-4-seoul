package com.capstone.all4seoul.user.dto;

import com.capstone.all4seoul.user.domain.Gender;
import com.capstone.all4seoul.user.domain.Mbti;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinUserRequest {
    @NotBlank(message = "로그인 ID를 입력해주세요.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String loginPassword;

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @NotNull(message = "생년월일을 입력해주세요.")
    @Past(message = "유효한 날짜를 입력해주세요.")
    private LocalDate birth;

    @NotNull(message = "MBTI를 입력해주세요.")
    private Mbti mbti;

    @NotNull(message = "성별을 선택해주세요.")
    private Gender gender;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

}

