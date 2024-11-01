package com.service.foodorderonline.repository;

import com.service.foodorderonline.model.IngredCategory;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredCategoryRepository extends JpaRepository<IngredCategory, Long> {
    Set<IngredCategory> findCategoriesByIdIn(Set<Long> ids);
}
