package com.maple.common.fixture;

import com.maple.common.boss.domain.Boss;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.FixedBossItemAmount;
import com.maple.common.bossitem.domain.RandomBossItem;
import com.maple.common.bossitem.domain.RandomBossItemAmount;
import com.maple.common.item.domain.Item;

public class BossItemFixture {

    public static FixedBossItemAmount createFixedBossAmount() {
        return new FixedBossItemAmount(1, 10);
    }

    public static RandomBossItemAmount createRandomBossItemAmount() {
        return new RandomBossItemAmount(1, 1);
    }

    public static FixedBossItem createFixedBossItem(Boss boss, Item item) {
        return new FixedBossItem(boss, item, createFixedBossAmount(), 1000L);
    }

    public static RandomBossItem createRandomBossItem(Boss boss, Item item) {
        return new RandomBossItem(boss, item, createRandomBossItemAmount());
    }
}
