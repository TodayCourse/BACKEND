package com.todayCourse.server.constant;

import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import lombok.Getter;

import java.util.Arrays;

public enum Vehicle {
    PUBLIC_TRANSPORT("대중교통"),
    CAR("자동차"),
    TAXI("택시"),
    WALK("도보"),
    BICYCLE("자전거");

    @Getter
    private String vehicle;

    Vehicle(String vehicle) { this.vehicle = vehicle; }

    public static Vehicle verifiedVehicle(String data) {
        return Arrays.stream(values())
                .filter(status -> data.trim().toUpperCase().equals(status.toString()))
                .findFirst()
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVALID_VALUES));
    }

}
