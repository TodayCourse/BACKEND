package com.todayCourse.server.auth.controller;

import com.todayCourse.server.auth.dto.LoginPostDto;
import com.todayCourse.server.auth.dto.TokenResponseDto;
import com.todayCourse.server.auth.service.AuthService;
import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import com.todayCourse.server.user.dto.UserPostDto;
import com.todayCourse.server.user.dto.UserResponseDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody @Valid UserPostDto userPostDto) {
        UserResponseDto userResponseDto = authService.signup(userPostDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginPostDto req,
                                               HttpServletResponse response) {

        TokenResponseDto tokenResponseDto = authService.login(req);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenResponseDto.getRefreshToken())
                .httpOnly(true)
                .secure(false) // 운영에서는 true 로 수정 필요
                .path("/")
                .maxAge(24 * 60 * 60) // 1일
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenResponseDto.getAccessToken())
                .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVALID_REFRESH_TOKEN));

        TokenResponseDto tokenResponseDto = authService.refresh(refreshToken);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenResponseDto.getRefreshToken())
                .httpOnly(true)
                .secure(false) // 운영에서는 true 로 수정 필요
                .path("/")
                .maxAge(24 * 60 * 60) // 1일
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());


        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenResponseDto.getAccessToken())
                .build();
    }
}
