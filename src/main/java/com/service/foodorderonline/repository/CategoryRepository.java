package com.service.foodorderonline.repository;

import com.service.foodorderonline.model.Category;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findCategoriesByIdIn(Set<Long> ids);
}
