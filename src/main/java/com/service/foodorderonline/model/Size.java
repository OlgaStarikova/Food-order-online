package com.service.foodorderonline.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@SQLDelete(sql = "UPDATE sizes SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction("is_deleted = FALSE")
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public Size() {
    }

    public Size(Long id) {
        this.id = id;
    }

}
