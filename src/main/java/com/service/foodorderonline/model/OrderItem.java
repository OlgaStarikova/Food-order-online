package com.service.foodorderonline.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@SQLDelete(sql = "UPDATE order_items SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction("is_deleted = FALSE")
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false, name = "dish_id")
    private Dish dish;
    @Column(nullable = false)
    private int quantity;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "title")
    private String title;
    @Column(name = "total_item_price", nullable = false)
    private BigDecimal totalItemPrice;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderItem",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemIngred> orderItemIngreds;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public OrderItem(Long id) {
        this.id = id;
    }

    public OrderItem() {
    }

    public BigDecimal countItemTotal() {
        return this.getPrice().add(
                this.getOrderItemIngreds().stream()
                        .map(i -> i.getPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public int getItemTimeCook() {
        return this.getDish().getTimecook();
    }
}
