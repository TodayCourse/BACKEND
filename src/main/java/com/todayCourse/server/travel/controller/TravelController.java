package com.todayCourse.server.travel.controller;

import com.todayCourse.server.travel.dto.TravelListResponseDto;
import com.todayCourse.server.travel.dto.TravelPatchDto;
import com.todayCourse.server.travel.dto.TravelPostDto;
import com.todayCourse.server.travel.entity.Travel;
import com.todayCourse.server.travel.mapper.TravelMapper;
import com.todayCourse.server.travel.service.TravelService;
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
@RequestMapping("/travel")
public class TravelController {
    private final TravelService travelService;
    private final TravelMapper travelMapper;

    // 여행 정보 등록
    @PostMapping("/create")
    public ResponseEntity postTravel(@RequestBody @Valid TravelPostDto travelPostDto) {
        Travel travel = travelService.createTravel(travelMapper.postDtoToTravel(travelPostDto));
        return new ResponseEntity<>(travelMapper.travelToTravelResponseDto(travel), HttpStatus.CREATED);
    }

    // 여행 정보 목록조회
    @GetMapping
    public ResponseEntity getTravelList() {
        List<Travel> travelList = travelService.getTravelList();
        return new ResponseEntity<>(travelMapper.travelToTravelListResponseDto(travelList), HttpStatus.OK);
    }
    // 여행 정보 상세조회
    @GetMapping("/{travelId}")
    public ResponseEntity getTravel(@PathVariable Long travelId) {
        Travel travel = travelService.getTravel(travelId);
        return new ResponseEntity<>(travelMapper.travelToTravelResponseDto(travel), HttpStatus.OK);
    }

    // 여행 정보 수정
    @PatchMapping("/{travelId}")
    public ResponseEntity patchTravel(@PathVariable Long travelId,
                                      @RequestBody @Valid TravelPatchDto travelPatchDto) {
        travelService.updateTravel(travelId, travelMapper.patchDtoToTravel(travelPatchDto));
        Travel travel = travelService.getTravel(travelId);
        return new ResponseEntity<>(travelMapper.travelToTravelResponseDto(travel), HttpStatus.OK);
    }

    // 여행 정보 삭제
    @DeleteMapping("/{travelId}")
    public ResponseEntity deleteTravel(@PathVariable Long travelId) {
        travelService.deleteTravel(travelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
