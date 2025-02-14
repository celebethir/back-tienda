package com.levelup.backend.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ClientUpdateDto {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String lastname;
    private Boolean bannedList;
}
