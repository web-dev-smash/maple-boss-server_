package com.maple.api.service;

import com.maple.api.service.dto.FixedBossItemCreateDto;
import com.maple.api.service.dto.RandomBossItemCreateDto;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.RandomBossItem;

public interface BossItemAppService {

    /**
     * 고정 보스 아이템 생성
     */
    FixedBossItem createFixedBossItem(FixedBossItemCreateDto dto);

    /**
     * 랜덤 보스 아이템 생성
     */
    RandomBossItem createRandomBossItem(RandomBossItemCreateDto dto);
}
