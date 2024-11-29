package com.service.foodorderonline.repository.dish;

import com.service.foodorderonline.model.SideDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SideDishRepository extends JpaRepository<SideDish, Long> {
}
