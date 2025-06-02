package com.todayCourse.server.user.mapper;

import com.todayCourse.server.user.dto.UserPatchDto;
import com.todayCourse.server.user.dto.UserPostDto;
import com.todayCourse.server.user.dto.UserResponseDto;
import com.todayCourse.server.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User postDtoToUser(UserPostDto userPostDto);

    default User patchDtoToUser(UserPatchDto userPatchDto) {
        if (userPatchDto == null) {
            return null;
        }

        User user = new User();

        user.setUserId(userPatchDto.getUserId());
        user.setEmail(userPatchDto.getEmail());
        user.setPassword(userPatchDto.getPassword());
        user.setUserName(userPatchDto.getUserName());
        user.setNickname(userPatchDto.getNickname());
        user.setPhone(userPatchDto.getPhone());

        return user;
    }

    default UserResponseDto userToUserResponseDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setNickname(user.getNickname());
        userResponseDto.setPhone(user.getPhone());

        return userResponseDto;
    }
}
