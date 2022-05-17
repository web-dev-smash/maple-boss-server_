package com.maple.api.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateDto {
    private String loginId;
    private String password;
    private String nickname;
    private String email;
}

