package com.capstone.all4seoul.user.dto.response;

import com.capstone.all4seoul.user.domain.Gender;
import com.capstone.all4seoul.user.domain.Mbti;
import com.capstone.all4seoul.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailUserResponse {
  private String loginId;
  private String userName;
  private LocalDate birth;
  private Mbti mbti;
  private Gender gender;
  private String nickname;
  private int credit;

  public static DetailUserResponse of(User user) {
    DetailUserResponse detailUserResponse = new DetailUserResponse();

    detailUserResponse.loginId = user.getLoginId();
    detailUserResponse.userName = user.getUsername();
    detailUserResponse.birth = user.getBirth();
    detailUserResponse.mbti = user.getMbti();
    detailUserResponse.gender = user.getGender();
    detailUserResponse.nickname = user.getNickname();
    detailUserResponse.credit = user.getCredit();

    return detailUserResponse;
  }
}
