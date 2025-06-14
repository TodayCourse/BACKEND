package com.todayCourse.server.category.controller;


import com.todayCourse.server.category.dto.TravelCategoryPatchDto;
import com.todayCourse.server.category.dto.TravelCategoryPostDto;
import com.todayCourse.server.category.entity.TravelCategory;
import com.todayCourse.server.category.mapper.TravelCategoryMapper;
import com.todayCourse.server.category.service.TravelCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/travelCategory")
public class TravelCategoryController {
    private final TravelCategoryService travelCategoryService;

    private final TravelCategoryMapper travelCategoryMapper;

    // 카테고리 정보 등록
    @PostMapping
    public ResponseEntity postCategory(@RequestBody @Valid TravelCategoryPostDto travelCategoryPostDto) {
        TravelCategory category = travelCategoryService.createCategory(travelCategoryMapper.postDtoToTravelCategory(travelCategoryPostDto));
        return new ResponseEntity<>(travelCategoryMapper.travelCategoryToTravelCategoryResponseDto(category), HttpStatus.CREATED);
    }

    // 카테고리 정보 목록조회
    @GetMapping
    public ResponseEntity getCategoryList() {
        List<TravelCategory> categoryList = travelCategoryService.getCategoryList();
        return new ResponseEntity<>(travelCategoryMapper.travelCategoryListToTravelCategoryListResponseDto(categoryList), HttpStatus.OK);
    }

    // 카테고리 정보 상세조회
    @GetMapping("/{categoryId}")
    public ResponseEntity getCategory(@PathVariable Long categoryId) {
        TravelCategory category = travelCategoryService.getCategory(categoryId);
        return new ResponseEntity<>(travelCategoryMapper.travelCategoryToTravelCategoryResponseDto(category), HttpStatus.OK);
    }

    // 카테고리 정보 수정
    @PatchMapping("/{categoryId}")
    public ResponseEntity patchCategory(@PathVariable Long categoryId,
                                        @RequestBody @Valid TravelCategoryPatchDto travelCategoryPatchDto) {
        travelCategoryService.updateCategory(categoryId, travelCategoryMapper.patchDtoToTravelCategory(travelCategoryPatchDto));
        TravelCategory category = travelCategoryService.getCategory(categoryId);
        return new ResponseEntity<>(travelCategoryMapper.travelCategoryToTravelCategoryResponseDto(category), HttpStatus.OK);
    }

    // 카테고리 정보 삭제
    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {
        travelCategoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
