package com.service.foodorderonline.repository.order;

import com.service.foodorderonline.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUserId(Long userId, Pageable pageable);

    Optional<Order> findOrdersByIdAndUserId(Long orderId, Long userId);
}
