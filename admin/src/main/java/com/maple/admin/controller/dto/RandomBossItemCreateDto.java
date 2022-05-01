package com.maple.admin.controller.dto;

import com.maple.common.bossitem.domain.RandomBossItem;
import com.maple.common.bossitem.domain.RandomBossItemAmount;
import lombok.val;

public class RandomBossItemCreateDto {

    public record RandomBossItemCreateRequest(
            long bossId,
            long itemId,
            int minimumAmount,
            int maximumAmount
    ) {
        public com.maple.admin.service.dto.RandomBossItemCreateDto toDto() {
            return new com.maple.admin.service.dto.RandomBossItemCreateDto(bossId, itemId, new RandomBossItemAmount(minimumAmount, maximumAmount));
        }
    }

    public record RandomBossItemCreateResponse(RandomBossItemCreateData fixedBossItem) {
    }

    public record RandomBossItemCreateData(
            long id,
            int minimumAmount,
            int maximumAmount
    ) {
        public static RandomBossItemCreateData create(RandomBossItem randomBossItem) {
            val amount = randomBossItem.getAmount();

            return new RandomBossItemCreateData(randomBossItem.getId(), amount.getMinimum(), amount.getMaximum());
        }
    }
}
