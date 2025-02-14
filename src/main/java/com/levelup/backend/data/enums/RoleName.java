package com.levelup.backend.data.enums;

import lombok.Getter;

@Getter
public enum RoleName {
    ADMIN("Admin"),
    USER("User");

    private final String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }
}
