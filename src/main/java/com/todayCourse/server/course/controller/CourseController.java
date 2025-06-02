package com.todayCourse.server.course.controller;

import com.todayCourse.server.course.dto.CoursePatchDto;
import com.todayCourse.server.course.entity.Course;
import com.todayCourse.server.course.dto.CoursePostDto;
import com.todayCourse.server.course.dto.CourseResponseDto;
import com.todayCourse.server.course.mapper.CourseMapper;
import com.todayCourse.server.course.service.CourseService;
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
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    // 코스 정보 등록
    @PostMapping("/{travelId}/courses")
    public ResponseEntity postCourse(@PathVariable Long travelId,
                                     @RequestBody List<CoursePostDto> coursePostDtoList) {

        List<Course> courseList = courseService.createCourse(travelId, coursePostDtoList);

        List<CourseResponseDto> courseResponseDtoList = courseList.stream()
                .map(courseMapper::courseToCourseResponseDto)
                .toList();

        return new ResponseEntity<>(courseResponseDtoList, HttpStatus.CREATED);
    }

    // 코스 목록 조회
    @GetMapping("/{travelId}/courses")
    public ResponseEntity getCourseList(@PathVariable Long travelId) {
        List<Course> courseList = courseService.getCourseList(travelId);

        List<CourseResponseDto> courseResponseDtoList = courseList.stream()
                .map(courseMapper::courseToCourseResponseDto)
                .toList();

        return new ResponseEntity<>(courseResponseDtoList, HttpStatus.OK);
    }

    // 코스 정보 수정
    @PatchMapping("/{travelId}/courses")
    public ResponseEntity patchCourse(@PathVariable Long travelId,
                                      @RequestBody List<CoursePatchDto> coursePatchDto) {
        List<Course> courseList = courseService.updateCourse(travelId, coursePatchDto);

        List<CourseResponseDto> courseResponseDtoList = courseList.stream()
                .map(courseMapper::courseToCourseResponseDto)
                .toList();

        return new ResponseEntity<>(courseResponseDtoList, HttpStatus.OK);
    }

    // 코스 정보 삭제
    @DeleteMapping("/{travelId}/courses/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable Long travelId,
                                       @PathVariable Long courseId) {

        courseService.deleteCourse(travelId, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
