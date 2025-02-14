package com.levelup.backend.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
public class Supplier {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String contact;

    @Column
    private String email;

    @Column
    private String web;

    @Column
    private String phone;

    @Column(name = "minimum_order")
    private int minimumOrder;
}
