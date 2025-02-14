package com.levelup.backend.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Wishlist {
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

    @OneToMany(mappedBy = "wishlistId", cascade = CascadeType.ALL)
    private List<WishlistProducts> wishlistProducts = new ArrayList<>();
}
