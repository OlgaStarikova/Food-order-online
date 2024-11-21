package com.service.foodorderonline.repository.dish;

import com.service.foodorderonline.model.DishIngred;
import com.service.foodorderonline.model.Ingred;
import com.service.foodorderonline.model.IngredCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DishIngredRepository extends JpaRepository<DishIngred, Long> {

    @Override
    Optional<DishIngred> findById(Long id);

    List<DishIngred> findDishIngredsByDishId(Long id);

    @Query("SELECT distinct ingredCategory FROM DishIngred dishIngred "
            + "JOIN dishIngred.ingred ingred "
            + "JOIN ingred.ingredCategory ingredCategory "
            + "WHERE dishIngred.dish.id = :dishId AND dishIngred.selected = FALSE")
    List<IngredCategory> findAllIngredCategoriesByDishId(
            @Param("dishId") Long dishId);

    @Query("SELECT distinct ingredCategory FROM DishIngred dishIngred "
            + "JOIN dishIngred.ingred ingred "
            + "JOIN ingred.ingredCategory ingredCategory "
            + "WHERE dishIngred.dish.id = :dishId AND dishIngred.selected = FALSE")
    List<IngredCategory> findNotDefaultIngredCategoriesByDishId(
            @Param("dishId") Long dishId);

    @Query("SELECT distinct ingred FROM DishIngred dishIngred "
            + "JOIN dishIngred.ingred ingred "
            + "WHERE dishIngred.dish.id = :dishId  AND dishIngred.selected = TRUE")
    List<Ingred> findDefaultIngredsByDishId(
            @Param("dishId") Long dishId);

    @Query("SELECT ingred FROM DishIngred dishIngred "
            + "JOIN dishIngred.ingred ingred "
            + "WHERE dishIngred.dish.id = :dishId "
            + "AND dishIngred.selected = FALSE "
            + "AND dishIngred.ingred.ingredCategory.id = :ingredCategoryId")
    List<Ingred> findNotDefaultIngredsInCategoryByDishId(
            @Param("dishId") Long dishId,
            @Param("ingredCategoryId") Long categoryId);

    @Query("SELECT ingred FROM DishIngred dishIngred "
            + "JOIN dishIngred.ingred ingred "
            + "WHERE dishIngred.dish.id = :dishId "
            + "AND dishIngred.ingred.ingredCategory.id = :ingredCategoryId")
    List<Ingred> findAllIngredsInCategoryByDishId(
            @Param("dishId") Long dishId,
            @Param("ingredCategoryId") Long categoryId);

    void deleteByDishId(Long id);
}
