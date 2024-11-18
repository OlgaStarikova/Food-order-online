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
@SQLDelete(sql = "UPDATE dishes SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction("is_deleted = FALSE")
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private int timecook;
    private String description;
    private String coverImage;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "category_id")
    private Category category;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dish",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishIngred> dishIngreds;
    @Column(nullable = false)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dish",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishSizePrice> dishSizePrices;
    @Column(nullable = false)
    private boolean isDeleted = false;
}
