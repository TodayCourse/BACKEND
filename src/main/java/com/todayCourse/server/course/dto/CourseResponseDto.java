package com.todayCourse.server.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {

    private Long courseId;

    private String placeName;

    private String address;

    private String lon;

    private String lat;

    private String openTime;

    private String closeTime;

    private String regUserId;

    private String mdfcUserId;
}
