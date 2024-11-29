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
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@SQLDelete(sql = "UPDATE ingreds SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction("is_deleted = FALSE")
@Table(name = "ingreds")
public class Ingred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String measure;
    @Column(nullable = false)
    private BigDecimal price;
    private String description;
    @Column(nullable = false)
    private int calories = 0;
    @Column(nullable = false)
    private int proteins = 0;
    @Column(nullable = false)
    private int fats = 0;
    @Column(nullable = false)
    private int carbogydrates = 0;
    private String coverImage;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false, name = "ingredcategory_id")
    private IngredCategory ingredCategory;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public Ingred() {
    }

    public Ingred(Long id) {
        this.id = id;
    }
}
