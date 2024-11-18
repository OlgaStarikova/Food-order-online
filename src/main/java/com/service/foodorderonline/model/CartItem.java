package com.service.foodorderonline.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE cart_items SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction("is_deleted = FALSE")
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "cart_id")
    private ShoppingCart shoppingCart;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "dish_id")
    private Dish dish;
    @Column(name = "base_price")
    private BigDecimal basePrice;
    @Column(nullable = false)
    private int quantity;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_items_ingreds",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "ingred_id")
    )
    private Set<Ingred> ingreds;
    private boolean isDeleted = false;

    public BigDecimal countItemTotal() {
        return this.getBasePrice().add(
                this.getIngreds().stream()
                        .map(i -> i.getPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
