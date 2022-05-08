package com.maple.admin.controller.dto;

import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.FixedBossItemAmount;
import lombok.val;

import java.time.OffsetDateTime;


public class FixedBossItemCreateDto {

    public record FixedBossItemCreateRequest(
            long bossId,
            long itemId,
            int minimumAmount,
            int maximumAmount,
            Long price
    ) {

        public com.maple.admin.service.dto.FixedBossItemCreateDto toDto() {
            return new com.maple.admin.service.dto.FixedBossItemCreateDto(bossId, itemId, new FixedBossItemAmount(minimumAmount, maximumAmount), price);
        }
    }

    public record FixedBossItemCreateResponse(FixedBossItemCreateData fixedBossItem) {
    }

    public record FixedBossItemCreateData(
            long id,
            int minimumAmount,
            int maximumAmount,
            long price,
            OffsetDateTime createAt
    ) {

        public static FixedBossItemCreateData create(FixedBossItem fixedBossItem) {
            val amount = fixedBossItem.getAmount();

            return new FixedBossItemCreateData(fixedBossItem.getId(), amount.getMinimum(), amount.getMaximum(), fixedBossItem.getPrice(), fixedBossItem.getCreateAt());
        }
    }
}
