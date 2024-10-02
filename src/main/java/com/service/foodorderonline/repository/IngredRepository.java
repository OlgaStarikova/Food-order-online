package com.service.foodorderonline.repository;

import com.service.foodorderonline.model.Ingred;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredRepository extends JpaRepository<Ingred, Long> {
    Set<Ingred> findIngredsByIdIn(Set<Long> ids);
}
