package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.BossClass;
import com.maple.common.bossitem.domain.BossItem;
import com.maple.common.item.domain.ItemType;

import java.time.OffsetDateTime;
import java.util.List;

public final class BossItemGetAllDto {

    public record BossItemGetAllResponse(List<BossItemGetAllData> bossItems) {
    }

    public record BossItemGetAllData(
            long id,
            String bossName,
            BossClass bossClass,
            String itemName,
            ItemType itemType,
            OffsetDateTime createAt
    ) {

        private BossItemGetAllData(BossItem bossItem) {
            this(
                    bossItem.getId(),
                    bossItem.getBoss().getName(),
                    bossItem.getBoss().getClazz(),
                    bossItem.getItem().getName(),
                    bossItem.getItem().getType(),
                    bossItem.getCreateAt()
            );
        }

        public static List<BossItemGetAllData> create(List<BossItem> bossItems) {
            return bossItems.stream().map(BossItemGetAllData::new).toList();
        }
    }
}
