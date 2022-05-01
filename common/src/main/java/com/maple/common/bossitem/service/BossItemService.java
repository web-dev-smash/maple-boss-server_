package com.maple.common.bossitem.service;

import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.RandomBossItem;

public interface BossItemService {

    /**
     * 고정 보스 아이템 생성
     */
    FixedBossItem createFixedBossItem(FixedBossItem fixedBossItem);

    /**
     * 고정 보스 아이템 생성
     */
    RandomBossItem createRandomBossItem(RandomBossItem randomBossItem);
}
