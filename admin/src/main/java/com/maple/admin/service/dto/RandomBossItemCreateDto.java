package com.maple.admin.service.dto;

import com.maple.common.bossitem.domain.RandomBossItemAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RandomBossItemCreateDto {
    private long bossId;
    private long itemId;
    private RandomBossItemAmount amount;
}
