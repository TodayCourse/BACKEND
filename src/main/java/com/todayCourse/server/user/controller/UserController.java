package com.todayCourse.server.user.controller;

import com.todayCourse.server.auth.security.CustomUserDetails;
import com.todayCourse.server.auth.service.AuthService;
import com.todayCourse.server.user.entity.User;
import com.todayCourse.server.user.mapper.UserMapper;
import com.todayCourse.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthService authService;

    @GetMapping("/mypage")
    public ResponseEntity getUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // SecurityContext 에서 가져온 사용자 정보
        Long userId = userDetails.getUser().getUserId();

        User user = userService.getUser(userId);

        return new ResponseEntity<>(userMapper.userToMyPageResponseDto(user), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(Authentication authentication,
                                       @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        // 인증된 사용자의 email 추출
        String email = (authentication != null) ? authentication.getName() : null;

        if (email != null && authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7);
            authService.logout(email, accessToken);
        }
        return ResponseEntity.ok().build();
    }

}
