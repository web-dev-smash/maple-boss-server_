package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.BossClass;
import com.maple.common.bossitem.domain.BossItem;
import com.maple.common.item.domain.ItemType;
import lombok.val;

import java.time.OffsetDateTime;
import java.util.List;

public final class BossItemDto {

    public record BossItemResponse(List<BossItemData> bossItems) {
    }

    public record BossItemData(
            long id,
            String bossName,
            BossClass bossClass,
            String itemName,
            ItemType itemType,
            OffsetDateTime createAt
    ) {

        public static List<BossItemData> create(List<BossItem> bossItems) {
            return bossItems.stream()
                    .map(BossItemData::create)
                    .toList();
        }

        private static BossItemData create(BossItem bossItem) {
            val boss = bossItem.getBoss();
            val item = bossItem.getItem();

            return new BossItemData(
                    bossItem.getId(), boss.getName(), boss.getClazz(),
                    item.getName(), item.getType(), bossItem.getCreateAt()
            );
        }
    }
}
