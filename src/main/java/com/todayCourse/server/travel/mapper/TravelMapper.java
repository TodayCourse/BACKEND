package com.todayCourse.server.travel.mapper;

import com.todayCourse.server.course.dto.CourseResponseDto;
import com.todayCourse.server.course.entity.Course;
import com.todayCourse.server.course.mapper.CourseMapper;
import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import com.todayCourse.server.travel.dto.TravelListResponseDto;
import com.todayCourse.server.travel.dto.TravelPatchDto;
import com.todayCourse.server.travel.dto.TravelPostDto;
import com.todayCourse.server.travel.dto.TravelResponseDto;
import com.todayCourse.server.travel.entity.Travel;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface TravelMapper {
    Travel postDtoToTravel(TravelPostDto travelPostDto);

    Travel patchDtoToTravel(TravelPatchDto travelPatchDto);

    default TravelResponseDto travelToTravelResponseDto(Travel travel) {
        TravelResponseDto travelResponseDto = new TravelResponseDto();

        travelResponseDto.setTravelId(travel.getTravelId());
        travelResponseDto.setTitle(travel.getTitle());
        travelResponseDto.setRegion(travel.getRegion());
        travelResponseDto.setTravelStartDt(travel.getTravelStartDt());
        travelResponseDto.setTravelEndDt(travel.getTravelEndDt());
        travelResponseDto.setCostType(travel.getCostType());
        travelResponseDto.setSeason(travel.getSeason());
        travelResponseDto.setVehicle(travel.getVehicle());
        travelResponseDto.setContents(travel.getContents());
        travelResponseDto.setRegUserId(travel.getRegUserId());
        travelResponseDto.setRegDtt(travel.getRegDtt());
        travelResponseDto.setMdfcUserId(travel.getMdfcUserId());
        travelResponseDto.setMdfcDtt(travel.getMdfcDtt());

        if (travel.getTravelCategory() != null) {
            travelResponseDto.setCategoryId(travel.getTravelCategory().getCategoryId());
            travelResponseDto.setCategoryName(travel.getTravelCategory().getCategoryName());
        }

        if (travel.getCourseList() != null) {
            travelResponseDto.setCourseList(travel.getCourseList().stream().map(this::mapCourseToDto).toList());
        }

        return travelResponseDto;
    }

    default List<TravelListResponseDto> travelToTravelListResponseDto(List<Travel> travels) {
        return travels.stream()
                .map(travel -> new TravelListResponseDto(
                        travel.getTravelId(),
                        travel.getTitle(),
                        travel.getRegion(),
                        travel.getTravelCategory().getCategoryName(),
                        travel.getTravelStartDt(),
                        travel.getTravelEndDt(),
                        travel.getCostType(),
                        travel.getSeason(),
                        travel.getVehicle(),
                        travel.getContents(),
                        travel.getRegUserId(),
                        travel.getMdfcUserId(),
                        travel.getCourseList() != null ? travel.getCourseList().size() : 0
                )).toList();
    }

    default List<TravelResponseDto> travelToTravelResponseDtoList(List<Travel> travels) {
        if (travels.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.TRAVEL_NOT_FOUND);
        }

        List<TravelResponseDto> travelResponseDtoList = new ArrayList<>();

        for (Travel travel : travels) {
            travelResponseDtoList.add(travelToTravelResponseDto(travel));
        }

        return travelResponseDtoList;
    }

    // CourseMapper 를 호출하기 위한 래퍼함수
    CourseResponseDto courseToCourseResponseDto(Course course);

    default CourseResponseDto mapCourseToDto(Course course) {
        return courseToCourseResponseDto(course);
    }
}
