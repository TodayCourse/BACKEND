package com.todayCourse.server.travel.service;

import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import com.todayCourse.server.travel.entity.Travel;
import com.todayCourse.server.travel.repository.TravelRepository;
import com.todayCourse.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {
    private final TravelRepository travelRepository;

    private final UserService userService;

    public Travel createTravel(Travel travel) {
        //TODO : 회원 존재 여부 로직 추가

        existTravel(travel.getTravelId());
        return travelRepository.save(travel);
    }

    public List<Travel> getTravelList() {
        return travelRepository.findAll();
    }

    public Travel getTravel(Long travelId) {
        return travelRepository.findById(travelId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TRAVEL_NOT_FOUND));
    }

    public Travel updateTravel(Long travelId, Travel travel) {
        Travel findTravel = verifiedTravel(travelId);

        if (travel.getTitle() != null) findTravel.setTitle(travel.getTitle());
        if (travel.getRegion() != null) findTravel.setRegion(travel.getRegion());
        if (travel.getTravelCategory() != null) findTravel.setTravelCategory(travel.getTravelCategory());
        if (travel.getTravelStartDt() != null) findTravel.setTravelStartDt(travel.getTravelStartDt());
        if (travel.getTravelEndDt() != null) findTravel.setTravelEndDt(travel.getTravelEndDt());
        if (travel.getCostType() != null) findTravel.setCostType(travel.getCostType());
        if (travel.getSeason() != null) findTravel.setSeason(travel.getSeason());
        if (travel.getVehicle() != null) findTravel.setVehicle(travel.getVehicle());
        if (travel.getContents() != null) findTravel.setContents(travel.getContents());
        if (travel.getRegUserId() != null) findTravel.setMdfcUserId(travel.getRegUserId());

        return findTravel;
    }

    public void deleteTravel(Long travelId) {
        verifiedTravel(travelId);
        travelRepository.deleteById(travelId);
    }

    public void existTravel(Long travelId) {
        Optional<Travel> travel = travelRepository.findByTravelId(travelId);
        if (travel.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.TRAVEL_EXIST);
        }
    }

    public Travel verifiedTravel(Long travelId) {
        return travelRepository.findByTravelId(travelId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TRAVEL_NOT_FOUND));
    }


}
