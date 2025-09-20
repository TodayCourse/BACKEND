package com.todayCourse.server.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    // common
    INVALID_VALUES(400, "Invalid Values Provided"),

    // auth
    EMAIL_EXISTS(400, "Email Exists"),
    INVALID_ROLE(400, "Invalid Role Provided"),
    INVALID_CREDENTIALS(401, "Invalid Email or Password"),
    ACCESS_DENIED(403, "Access Denied"),
    INVALID_TOKEN(401, "Invalid Token"),
    INVALID_REFRESH_TOKEN(401, "Invalid Refresh Token"),
    REFRESH_NOT_FOUND(404, "Refresh Not Found"),
    REFRESH_MISMATCH(401, "Refresh Token Mismatch"),


    // user
    USER_EXISTS(400, "User Exists"),
    USER_NOT_FOUND(404, "User Not Found"),

    // travel
    TRAVEL_EXIST(400, "Travel Exists"),
    TRAVEL_NOT_FOUND(404, "Travel Not Found"),

    // course
    COURSE_EXIST(400, "Course Exists"),
    COURSE_NOT_FOUND(404, "Course Not Found"),
    COURSES_EXCEED_FIVE(400, "Exceed 5 Courses"),

    // category
    CATEGORY_EXIST(400, "Category Exists"),
    CATEGORY_NOT_FOUND(404, "Category Not Found");

    private int status;
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
