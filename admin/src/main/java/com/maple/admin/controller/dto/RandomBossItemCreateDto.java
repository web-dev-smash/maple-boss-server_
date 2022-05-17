package com.maple.admin.controller.dto;

import com.maple.common.bossitem.domain.RandomBossItem;
import com.maple.common.bossitem.domain.RandomBossItemAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.time.OffsetDateTime;

public class RandomBossItemCreateDto {

    @Getter
    @AllArgsConstructor
    public static class RandomBossItemCreateRequest {
        private long bossId;
        private long itemId;
        private int minimumAmount;
        private int maximumAmount;

        public com.maple.admin.service.dto.RandomBossItemCreateDto toDto() {
            return new com.maple.admin.service.dto.RandomBossItemCreateDto(bossId, itemId, new RandomBossItemAmount(minimumAmount, maximumAmount));
        }
    }

    @Getter
    @AllArgsConstructor
    public static class RandomBossItemCreateResponse {
        private RandomBossItemCreateData randomBossItem;
    }

    @Getter
    @AllArgsConstructor
   public static class RandomBossItemCreateData {
        private long id;
        private int minimumAmount;
        private int maximumAmount;
        private OffsetDateTime createAt;

        public static RandomBossItemCreateData create(RandomBossItem randomBossItem) {
            val amount = randomBossItem.getAmount();

            return new RandomBossItemCreateData(randomBossItem.getId(), amount.getMinimum(), amount.getMaximum(), randomBossItem.getCreateAt());
        }
   }
}
