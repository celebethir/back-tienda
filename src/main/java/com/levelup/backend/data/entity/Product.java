package com.levelup.backend.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne()
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private Category category;

    @OneToOne()
    @JoinColumn(
            name = "supplier_id",
            nullable = false
    )
    private Supplier supplier;

    @Column(name = "buy_price", nullable = false)
    private BigDecimal buyPrice;

    @Column(name = "sell_price", nullable = false)
    private BigDecimal sellPrice;

    @Column(name = "bar_code")
    private int barCode;

    @Column()
    private int inventory;

    @Column()
    private String image;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistProducts> wishlistProducts;
}
