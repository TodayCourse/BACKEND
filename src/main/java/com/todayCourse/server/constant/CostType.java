package com.todayCourse.server.constant;

import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import lombok.Getter;

import java.util.Arrays;

public enum CostType {
    UNDER_100K("10만원 이하"),
    BETWEEN_100K_200K("10만원 ~ 20만원"),
    BETWEEN_200K_300K("20만원 ~ 30만원"),
    BETWEEN_300K_400K("30만원 ~ 40만원"),
    BETWEEN_400K_500K("40만원 ~ 50만원"),
    OVER_500K("50만원 이상");

    @Getter
    private String costType;

    CostType(String costType) {
        this.costType = costType;
    }

    public static CostType verifiedCostType(String data) {
        return Arrays.stream(values())
                .filter(status -> data.trim().toUpperCase().equals(status.toString()))
                .findFirst()
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVALID_VALUES));
    }
}
