package com.service.foodorderonline.repository.dish;

import com.service.foodorderonline.model.Dish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long>, JpaSpecificationExecutor<Dish> {
    @Override
    Optional<Dish> findById(Long dishId);

    List<Dish> findDishesByCategoryId(Long id);
}
