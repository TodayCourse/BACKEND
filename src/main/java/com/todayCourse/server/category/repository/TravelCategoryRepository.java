package com.todayCourse.server.category.repository;

import com.todayCourse.server.category.entity.TravelCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelCategoryRepository extends JpaRepository<TravelCategory, Long> {
    Optional<TravelCategory> findByCategoryId(Long categoryId);
}
