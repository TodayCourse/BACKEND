package com.todayCourse.server.constant.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER("일반 사용자"),
    ROLE_ADMIN("관리자");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    @JsonValue
    public String getRoleName() {
        return roleName;
    }
}
