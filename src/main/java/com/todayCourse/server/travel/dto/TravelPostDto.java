package com.todayCourse.server.travel.dto;

import com.todayCourse.server.constant.CostType;
import com.todayCourse.server.constant.Region;
import com.todayCourse.server.constant.Season;
import com.todayCourse.server.constant.Vehicle;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class TravelPostDto {

    @NotBlank
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
}
