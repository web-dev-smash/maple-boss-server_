package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.BossClass;
import com.maple.common.bossitem.domain.BossItem;
import com.maple.common.item.domain.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class BossItemGetAllDto {

    @Getter
    @AllArgsConstructor
    public static class BossItemGetAllResponse {
        private List<BossItemGetAllData> bossItems;
    }

    @Getter
    @AllArgsConstructor
    public static class BossItemGetAllData {
        private long id;
        private String bossName;
        private BossClass bossClass;
        private String itemName;
        private ItemType itemType;
        private OffsetDateTime createAt;

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
            return bossItems.stream().map(BossItemGetAllData::new).collect(toList());
        }
    }
}
