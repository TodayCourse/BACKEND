package com.todayCourse.server.travel.dto;

import com.todayCourse.server.constant.CostType;
import com.todayCourse.server.constant.Region;
import com.todayCourse.server.constant.Season;
import com.todayCourse.server.constant.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelListResponseDto {
    private Long travelId;

    private String title;

    private Region region;

    private String category;

    private String travelStartDt;

    private String travelEndDt;

    private CostType costType;

    private Season season;

    private Vehicle vehicle;

    private String contents;

    private String regUserId;

    private String mdfcUserId;

    private int courseCount;
}
