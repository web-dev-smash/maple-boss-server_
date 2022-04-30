package com.maple.common.fixture;

import com.maple.common.bossitem.domain.FixedBossAmount;

public class BossItemFixture {

    public static FixedBossAmount createFixedBossAmount() {
        return new FixedBossAmount(1, 10);
    }
}
