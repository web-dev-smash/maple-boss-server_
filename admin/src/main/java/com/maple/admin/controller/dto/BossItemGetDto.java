package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.BossClass;
import com.maple.common.bossitem.domain.BossItem;
import com.maple.common.item.domain.ItemType;
import lombok.val;

import java.time.OffsetDateTime;
import java.util.List;

public final class BossItemGetDto {

    public record BossItemsGetResponse(List<BossItemsGetData> bossItems) {
    }

    public record BossItemsGetData(
            long id,
            String bossName,
            BossClass bossClass,
            String itemName,
            ItemType itemType,
            OffsetDateTime createAt
    ) {

        public static List<BossItemsGetData> create(List<BossItem> bossItems) {
            return bossItems.stream()
                    .map(BossItemsGetData::create)
                    .toList();
        }

        private static BossItemsGetData create(BossItem bossItem) {
            val boss = bossItem.getBoss();
            val item = bossItem.getItem();

            return new BossItemsGetData(
                    bossItem.getId(), boss.getName(), boss.getClazz(),
                    item.getName(), item.getType(), bossItem.getCreateAt()
            );
        }
    }
}
