package com.todayCourse.server.category.mapper;

import com.todayCourse.server.category.dto.TravelCategoryListResponseDto;
import com.todayCourse.server.category.dto.TravelCategoryPatchDto;
import com.todayCourse.server.category.dto.TravelCategoryPostDto;
import com.todayCourse.server.category.dto.TravelCategoryResponseDto;
import com.todayCourse.server.category.entity.TravelCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TravelCategoryMapper {
    TravelCategory postDtoToTravelCategory(TravelCategoryPostDto travelCategoryPostDto);

    TravelCategory patchDtoToTravelCategory(TravelCategoryPatchDto travelCategoryPatchDto);

    TravelCategoryResponseDto travelCategoryToTravelCategoryResponseDto(TravelCategory travelCategory);

    List<TravelCategoryListResponseDto> travelCategoryListToTravelCategoryListResponseDto(List<TravelCategory> travelCategoryList);
}
