package com.todayCourse.server.auth.service;

import com.todayCourse.server.auth.dto.LoginPostDto;
import com.todayCourse.server.auth.dto.TokenResponseDto;
import com.todayCourse.server.auth.security.JwtTokenProvider;
import com.todayCourse.server.constant.type.ActiveStatus;
import com.todayCourse.server.constant.type.LoginType;
import com.todayCourse.server.constant.type.Role;
import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import com.todayCourse.server.user.dto.UserPostDto;
import com.todayCourse.server.user.dto.UserResponseDto;
import com.todayCourse.server.user.entity.User;
import com.todayCourse.server.user.mapper.UserMapper;
import com.todayCourse.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository users;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwt;

    @Transactional
    public UserResponseDto signup(UserPostDto userPostDto) {
        if (users.existsByEmail(userPostDto.getEmail())) throw new BusinessLogicException(ExceptionCode.EMAIL_EXISTS);

        User user = userMapper.postDtoToUser(userPostDto);

        // 비밀번호 인코딩, 기본 권한, 상태 설정
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.ROLE_USER));
        user.setActiveStatus(ActiveStatus.ACTIVE);
        user.setLoginType(LoginType.BASIC);

        users.save(user);

        return userMapper.userToUserResponseDto(user);
    }

    public TokenResponseDto login(LoginPostDto req) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword());
            authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new BusinessLogicException(ExceptionCode.INVALID_CREDENTIALS);
        }


        User user = users.findByEmail(req.getEmail()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        String access = jwt.createAccessToken(user.getEmail(), user.getRoles());
        String refresh = jwt.createRefreshToken(user.getEmail());

        return TokenResponseDto.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .tokenType("Bearer")
                .build();
    }

    public TokenResponseDto refresh(String refreshToken) {
        if (!jwt.validate(refreshToken)) throw new BusinessLogicException(ExceptionCode.INVALID_REFRESH_TOKEN);

        String email = jwt.getEmail(refreshToken);
        String stored = jwt.getStoredRefreshToken(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.REFRESH_NOT_FOUND));

        if (!stored.equals(refreshToken)) throw new BusinessLogicException(ExceptionCode.REFRESH_MISMATCH);

        User user = users.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        var newAccess = jwt.createAccessToken(email, user.getRoles());
        var newRefresh = jwt.createRefreshToken(email); // rotate

        return TokenResponseDto.builder()
                .accessToken(newAccess)
                .refreshToken(newRefresh)
                .tokenType("Bearer")
                .build();
    }

    public void logout(String email, String accessToken) {
        jwt.deleteRefreshToken(email);
        jwt.blacklistAccessToken(accessToken);
    }
}
