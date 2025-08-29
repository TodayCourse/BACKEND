package com.todayCourse.server.travel.dto;

import com.todayCourse.server.constant.type.CostType;
import com.todayCourse.server.constant.type.Region;
import com.todayCourse.server.constant.type.Season;
import com.todayCourse.server.constant.type.Vehicle;
import com.todayCourse.server.course.dto.CourseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelResponseDto {
    private Long travelId;

    private String title;

    private Region region;

    private String travelStartDt;

    private String travelEndDt;

    private CostType costType;

    private Season season;

    private Vehicle vehicle;

    private String contents;

    private String regUserId;

    private LocalDateTime regDtt;

    private String mdfcUserId;

    private LocalDateTime mdfcDtt;

    private Long categoryId;

    private String categoryName;

    private List<CourseResponseDto> courseList;
}
