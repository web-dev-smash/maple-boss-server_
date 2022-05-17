package com.maple.api.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PartyCreateDto{
    private long leaderId;
    private String name;
    private String description;
}
