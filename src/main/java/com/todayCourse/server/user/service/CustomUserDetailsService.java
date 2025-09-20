package com.todayCourse.server.user.service;

import com.todayCourse.server.auth.security.CustomUserDetails;
import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import com.todayCourse.server.user.entity.User;
import com.todayCourse.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return new CustomUserDetails(user);
    }
}
