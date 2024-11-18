package com.service.foodorderonline.repository.dish;

import com.service.foodorderonline.model.Size;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Override
    Optional<Size> findById(Long sizeId);
}
