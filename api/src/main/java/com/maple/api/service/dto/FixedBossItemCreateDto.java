package com.maple.api.service.dto;

import com.maple.common.bossitem.domain.FixedBossItemAmount;

public record FixedBossItemCreateDto(
        long bossId,
        long itemId,
        FixedBossItemAmount amount,
        long price
) {
}
