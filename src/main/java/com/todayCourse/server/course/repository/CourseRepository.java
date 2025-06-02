package com.todayCourse.server.course.repository;

import com.todayCourse.server.course.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findByCourseId(Long courseId);
    List<Course> findByTravel_TravelIdOrderByCourseId(Long travelId);
}
