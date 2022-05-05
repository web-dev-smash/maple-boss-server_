package com.maple.common.bossitem.service;

import com.maple.common.bossitem.domain.BossItem;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.RandomBossItem;

import java.util.List;

public interface BossItemService {

    /**
     * 고정 보스 아이템 생성
     */
    FixedBossItem createFixedBossItem(FixedBossItem fixedBossItem);

    /**
     * 고정 보스 아이템 생성
     */
    RandomBossItem createRandomBossItem(RandomBossItem randomBossItem);

    /**
     * 보스 아이템 목록 조회
     */
    List<BossItem> getBossItems(long bossId);
}
