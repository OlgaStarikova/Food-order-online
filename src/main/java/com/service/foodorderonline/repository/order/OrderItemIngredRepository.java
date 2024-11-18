package com.service.foodorderonline.repository.order;

import com.service.foodorderonline.model.OrderItemIngred;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemIngredRepository extends JpaRepository<OrderItemIngred, Long> {
    List<OrderItemIngred> findOrderItemIngredsByOrderItemId(Long orderItemId);

    Optional<OrderItemIngred> findByIdAndOrderItemId(Long orderItemIngredId, Long orderItemId);

    void deleteAllByOrderItemId(Long orderItemId);
}
