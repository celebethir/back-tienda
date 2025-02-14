package com.levelup.backend.data.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String orderStatus;

    OrderStatusEnum(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
