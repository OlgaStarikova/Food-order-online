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
import jakarta.persistence.OneToOne;
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
@SQLDelete(sql = "UPDATE categories SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction("is_deleted = FALSE")
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String coverImageMain;
    private String coverImageAddl;
    @Column
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "categories_ingreds",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "ingred_id")
    )
    private List<Ingred> ingreds;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false, name = "constructor_id")
    private Dish constructor;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }
}
