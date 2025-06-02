package com.todayCourse.server.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCategoryPatchDto {

    @NotBlank
    private String categoryName;

    private String regUserId;

    private String mdfcUserId;
}
