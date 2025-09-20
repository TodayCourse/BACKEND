package com.todayCourse.server.user.mapper;

import com.todayCourse.server.constant.type.Role;
import com.todayCourse.server.user.dto.MyPageResponseDto;
import com.todayCourse.server.user.dto.UserPatchDto;
import com.todayCourse.server.user.dto.UserPostDto;
import com.todayCourse.server.user.dto.UserResponseDto;
import com.todayCourse.server.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

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
        user.setLoginId(userPatchDto.getLoginId());
        user.setNickname(userPatchDto.getNickname());

        return user;
    }

    UserResponseDto userToUserResponseDto(User user);

    @Mapping(target = "password", qualifiedByName = "maskPassword")
    @Mapping(target = "roles", expression = "java(mapSingleRole(user.getRoles()))")
    MyPageResponseDto userToMyPageResponseDto(User user);

    @Named("maskPassword")
    default String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "";
        }
        return "*".repeat(password.length());
    }

    default String mapSingleRole(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) return null;
        // 첫 번째 role 하나만 반환
        return roles.iterator().next().getRoleName();
    }
}
