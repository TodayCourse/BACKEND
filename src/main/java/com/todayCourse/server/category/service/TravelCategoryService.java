package com.todayCourse.server.category.service;

import com.todayCourse.server.category.repository.TravelCategoryRepository;
import com.todayCourse.server.category.entity.TravelCategory;
import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelCategoryService {
    private final TravelCategoryRepository categoryRepository;

    public TravelCategory createCategory(TravelCategory category) {
        existCategory(category.getCategoryId());
        return categoryRepository.save(category);
    }

    public List<TravelCategory> getCategoryList() {
        return categoryRepository.findAll();
    }

    public TravelCategory getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }

    public void updateCategory(Long categoryId, TravelCategory category) {
        TravelCategory findCategory = verifiedCategory(categoryId);

        if (category.getCategoryName() != null) findCategory.setCategoryName(category.getCategoryName());
        if (category.getSortOrder() > 0) findCategory.setSortOrder(category.getSortOrder());
        if (category.getMdfcUserId() != null) findCategory.setMdfcUserId(category.getMdfcUserId());
    }

    public void deleteCategory(Long categoryId) {
        verifiedCategory(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    public void existCategory(Long categoryId) {
        Optional<TravelCategory> category = categoryRepository.findByCategoryId(categoryId);
        if (category.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.CATEGORY_EXIST);
        }
    }

    public TravelCategory verifiedCategory(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }
}
