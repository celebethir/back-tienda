package com.levelup.backend.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
public class Client {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column()
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column()
    private String lastname;

    @Column(name = "banned_list")
    private boolean bannedList;
}
