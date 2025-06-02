package com.todayCourse.server.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCategoryResponseDto {
    private Long categoryId;

    private String categoryName;

    private int sortOrder;

    private String regUserId;

    private String mdfcUserId;

    private LocalDateTime regDtt;

    private LocalDateTime mdfcDtt;
}
