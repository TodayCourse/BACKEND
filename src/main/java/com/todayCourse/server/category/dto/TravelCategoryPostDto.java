package com.todayCourse.server.category.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class TravelCategoryPostDto {

    @NotBlank
    private String categoryName;

    @Column
    private int sortOrder;

    @Column
    private String regUserId;
}
