package com.maple.admin.controller.dto;

import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.FixedBossItemAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.time.OffsetDateTime;


public class FixedBossItemCreateDto {

    @Getter
    @AllArgsConstructor
    public static class FixedBossItemCreateRequest{
        private long bossId;
        private long itemId;
        private int minimumAmount;
        private int maximumAmount;
        private Long price;

        public com.maple.admin.service.dto.FixedBossItemCreateDto toDto() {
            return new com.maple.admin.service.dto.FixedBossItemCreateDto(bossId, itemId, new FixedBossItemAmount(minimumAmount, maximumAmount), price);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class FixedBossItemCreateResponse {
        private FixedBossItemCreateData fixedBossItem;
    }

    @Getter
    @AllArgsConstructor
    public static class FixedBossItemCreateData {
        private long id;
        private int minimumAmount;
        private int maximumAmount;
        private long price;
        private OffsetDateTime createAt;

        public static FixedBossItemCreateData create(FixedBossItem fixedBossItem) {
            val amount = fixedBossItem.getAmount();

            return new FixedBossItemCreateData(fixedBossItem.getId(), amount.getMinimum(), amount.getMaximum(), fixedBossItem.getMeso(), fixedBossItem.getCreateAt());
        }
    }
}
