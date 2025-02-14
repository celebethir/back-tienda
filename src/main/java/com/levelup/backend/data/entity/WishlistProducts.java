package com.levelup.backend.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class WishlistProducts {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne()
    @JoinColumn(
            name = "wishlist_id",
            nullable = false
    )
    private Wishlist wishlistId;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;
}
