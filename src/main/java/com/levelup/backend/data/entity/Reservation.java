package com.levelup.backend.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Setter
@Getter
public class Reservation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @OneToOne()
    @JoinColumn(
            name = "client_id",
            nullable = false
    )
    private Client clientId;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Product> productId;

    @OneToOne()
    @JoinColumn(
            name = "status_id",
            nullable = false
    )
    private OrderStatus statusId;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column()
    private BigDecimal upfront;
}
