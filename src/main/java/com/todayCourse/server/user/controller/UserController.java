package com.todayCourse.server.user.controller;

import com.todayCourse.server.user.dto.UserPostDto;
import com.todayCourse.server.user.entity.User;
import com.todayCourse.server.user.mapper.UserMapper;
import com.todayCourse.server.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/join")
    public ResponseEntity postUser(@RequestBody @Valid UserPostDto userPostDto) {
        User user = userService.joinUser(userMapper.postDtoToUser(userPostDto));
        return new ResponseEntity<>(userMapper.userToUserResponseDto(user), HttpStatus.CREATED);
    }

}
