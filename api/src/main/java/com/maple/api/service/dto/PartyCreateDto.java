package com.maple.api.service.dto;

public record PartyCreateDto(
        long leaderId,
        String name,
        String description
) {
}
