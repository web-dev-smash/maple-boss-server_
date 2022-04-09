package com.maple.fixture;

import com.maple.domain.boss.domain.Boss;
import com.maple.domain.boss.domain.BossClass;

public class BossFixture {

    public static Boss createBoss() {
        return new Boss("윌", 1, BossClass.EASY, 1, 2, 100L, 200L, 300L, 400L, 100, 5);
    }
}
