package com.todayCourse.server.course.mapper;

import com.todayCourse.server.course.entity.Course;
import com.todayCourse.server.course.dto.CoursePatchDto;
import com.todayCourse.server.course.dto.CoursePostDto;
import com.todayCourse.server.course.dto.CourseResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course postDtoToCourse(CoursePostDto coursePostDto);

    Course patchDtoToCourse(CoursePatchDto coursePatchDto);

    CourseResponseDto courseToCourseResponseDto(Course course);
}
