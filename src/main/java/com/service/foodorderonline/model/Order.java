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
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE orders SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction("is_deleted = FALSE")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private String firstName;
    private String secondName;
    @Column(nullable = false, unique = true, columnDefinition =
            "status")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Status status = Status.PENDING;

    @Column(nullable = false, unique = true, columnDefinition =
            "expType")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ExpType expectationTimeType = ExpType.FREE;

    @Column(nullable = false, unique = true, columnDefinition =
            "payType")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private PayType paymentType = PayType.GIVING;

    private String expectHour;
    private String expectMinute;

    @Column(nullable = false, unique = true, columnDefinition =
            "expDay")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ExpDay day = ExpDay.TODAY;

    @Column(nullable = false)
    private BigDecimal total;
    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();
    @Column(nullable = false)
    private String shippingAddress;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public enum Status {
        PENDING,
        COOKING,
        READY,
        DELIVERED,
        CANCELED
    }

    public enum ExpType {
        CLEAR,
        FREE
    }

    public enum PayType {
        GIVING
    }

    public enum ExpDay {
        TODAY,
        TOMORROW
    }

    public BigDecimal countOrderTotal() {
        return this.getOrderItems().stream()
                .map(i -> i.getTotalItemPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
