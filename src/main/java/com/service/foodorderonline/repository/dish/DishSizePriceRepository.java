package com.service.foodorderonline.repository.dish;

import com.service.foodorderonline.model.DishSizePrice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DishSizePriceRepository extends JpaRepository<DishSizePrice, Long>,
        JpaSpecificationExecutor<DishSizePrice> {

    @Override
    Optional<DishSizePrice> findById(Long id);

    List<DishSizePrice> findDishSizePricesByDishId(Long id);
}
