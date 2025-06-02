package com.todayCourse.server.course.service;

import com.todayCourse.server.course.dto.CoursePatchDto;
import com.todayCourse.server.course.entity.Course;
import com.todayCourse.server.course.dto.CoursePostDto;
import com.todayCourse.server.course.mapper.CourseMapper;
import com.todayCourse.server.course.repository.CourseRepository;
import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import com.todayCourse.server.travel.entity.Travel;
import com.todayCourse.server.travel.service.TravelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final TravelService travelService;

    public List<Course> createCourse(Long travelId, List<CoursePostDto> coursePostDtoList) {
        List<Course> savedCourseList = new ArrayList<>();

        validateSize(coursePostDtoList);

        Travel travel = travelService.verifiedTravel(travelId);

        for (CoursePostDto dto : coursePostDtoList) {
            Course course = courseMapper.postDtoToCourse(dto);
            travel.addCourse(course);

            savedCourseList.add(courseRepository.save(course));
        }
        return savedCourseList;
    }

    public List<Course> getCourseList(Long travelId) {
        Travel findTravel = travelService.verifiedTravel(travelId);
        return courseRepository.findByTravel_TravelIdOrderByCourseId(findTravel.getTravelId());
    }

    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COURSE_NOT_FOUND));
    }

    public List<Course> updateCourse(Long travelId, List<CoursePatchDto> coursePatchDtoList) {
        List<Course> savedCourseList = new ArrayList<>();

        validateSize(coursePatchDtoList);

        Travel travel = travelService.verifiedTravel(travelId);

        for (CoursePatchDto dto : coursePatchDtoList) {
            Course course = courseMapper.patchDtoToCourse(dto);

            Course findCourse = verifiedCourse(course.getCourseId());

            // 수정하려는 코스가 기존 여행에 속해 있는 코스인지 검증
            if (!findCourse.getTravel().getTravelId().equals(travelId)) {
                throw new BusinessLogicException(ExceptionCode.INVALID_VALUES);
            }

            if (course.getPlaceName() != null) findCourse.setPlaceName(course.getPlaceName());
            if (course.getAddress() != null) findCourse.setAddress(course.getAddress());
            if (course.getLon() != null) findCourse.setLon(course.getLon());
            if (course.getLat() != null) findCourse.setLat(course.getLat());
            if (course.getOpenTime() != null) findCourse.setOpenTime(course.getOpenTime());
            if (course.getCloseTime() != null) findCourse.setCloseTime(course.getCloseTime());
            if (course.getMdfcUserId() != null) findCourse.setMdfcUserId(course.getMdfcUserId());

            travel.addCourse(findCourse);

            savedCourseList.add(courseRepository.save(findCourse));
        }
        return savedCourseList;
    }

    public void deleteCourse(Long travelId, Long courseId) {
        Course findCourse = verifiedCourse(courseId);

        if (!findCourse.getTravel().getTravelId().equals(travelId)) {
            throw new BusinessLogicException(ExceptionCode.INVALID_VALUES);
        }

        courseRepository.deleteById(courseId);
    }

    public Course verifiedCourse(Long courseId) {
        return courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COURSE_NOT_FOUND));
    }

    // 코스 최대 허용 개수 (5개) 검증
    public void validateSize(List<?> dtoList) {
        if (dtoList.size() > 5) {
            throw new BusinessLogicException(ExceptionCode.COURSES_EXCEED_FIVE);
        }
    }
}
