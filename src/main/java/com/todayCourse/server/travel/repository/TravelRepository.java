package com.todayCourse.server.travel.repository;

import com.todayCourse.server.travel.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    Optional<Travel> findByTravelId(Long userId);
}
