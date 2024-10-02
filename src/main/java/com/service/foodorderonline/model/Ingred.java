package com.service.foodorderonline.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String name;
    @Column(nullable = false, unique = true, columnDefinition =
            "enum('g', 'tbsp', 'tsp', 'pcs')")
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure measure = UnitOfMeasure.WEIGHT1G;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private BigDecimal price;
    private String description;
    private String coverImage;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public enum UnitOfMeasure {
        WEIGHT1G("g"),
        TBSP("tbsp"),
        TSP("tsp"),
        PCS("pcs");

        private String unit;

        UnitOfMeasure(String unitM) {
            this.unit = unitM;
        }

        public String getUnit() {
            return unit;
        }
    }
}
