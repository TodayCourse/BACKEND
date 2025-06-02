package com.todayCourse.server.constant;

import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import lombok.Getter;

import java.util.Arrays;

public enum ActiveStatus {
    ACTIVE("활성계정"),
    INACTIVE("비활성계정"),
    DORMANT("휴먼계정");

    @Getter
    private String activeStatus;

    ActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public static ActiveStatus verifiedActiveStatus(String data) {
        return Arrays.stream(values())
                .filter(status -> data.trim().toUpperCase().equals(status.toString()))
                .findFirst()
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVALID_VALUES));
    }
}
