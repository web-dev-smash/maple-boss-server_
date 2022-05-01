package com.maple.api.service.dto;

import com.maple.common.bossitem.domain.RandomBossItemAmount;

public record RandomBossItemCreateDto(
        long bossId,
        long itemId,
        RandomBossItemAmount amount
) {
}
