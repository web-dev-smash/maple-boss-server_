package com.maple.api.service.dto;

public record UserCreateDto(
        String loginId,
        String password,
        String nickname,
        String email
) {
}
