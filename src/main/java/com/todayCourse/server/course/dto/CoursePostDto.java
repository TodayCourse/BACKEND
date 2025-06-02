package com.todayCourse.server.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CoursePostDto {

    @NotBlank
    private String placeName;

    private String address;

    private String lon;

    private String lat;

    private String openTime;

    private String closeTime;

    private String regUserId;
}
