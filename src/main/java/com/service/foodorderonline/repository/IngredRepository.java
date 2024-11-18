package com.service.foodorderonline.repository;

import com.service.foodorderonline.model.Ingred;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredRepository extends JpaRepository<Ingred, Long> {
    List<Ingred> findIngredsByIngredCategoryId(Long id);
}
