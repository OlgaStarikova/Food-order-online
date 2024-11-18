package com.service.foodorderonline.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@SQLDelete(sql = "UPDATE dishes_ingreds SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction("is_deleted = FALSE")
@Table(name = "dishes_ingreds")
public class DishIngred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "dish_id")
    private Dish dish;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false, name = "ingred_id")
    private Ingred ingred;
    @Column(nullable = false)
    private boolean isDefault = false;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public DishIngred() {
    }

    public DishIngred(Long id) {
        this.id = id;
    }
}
