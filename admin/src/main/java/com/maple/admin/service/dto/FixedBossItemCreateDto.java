package com.maple.admin.service.dto;

import com.maple.common.bossitem.domain.FixedBossItemAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FixedBossItemCreateDto {
    private long bossId;
    private long itemId;
    private FixedBossItemAmount amount;
    private long price;
}

