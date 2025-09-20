package com.todayCourse.server.user.dto;

import com.todayCourse.server.constant.type.ActiveStatus;
import com.todayCourse.server.constant.type.LoginType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponseDto {

    private Long userId;
    private String email;
    private String password;
    private String loginId;
    private String nickname;
    private LoginType loginType;
    private String roles;
    private ActiveStatus activeStatus;
}
