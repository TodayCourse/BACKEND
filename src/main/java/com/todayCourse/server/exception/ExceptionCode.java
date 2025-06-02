package com.todayCourse.server.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    // common
    INVALID_VALUES(400, "Invalid Values Provided"),

    // user
    MEMBER_EXISTS(400, "Member Exists"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),

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
