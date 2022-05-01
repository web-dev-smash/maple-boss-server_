package com.maple.admin.service;

import com.maple.admin.service.dto.FixedBossItemCreateDto;
import com.maple.admin.service.dto.RandomBossItemCreateDto;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.RandomBossItem;

public interface BossItemAdminService {

    /**
     * 고정 보스 아이템 생성
     */
    FixedBossItem createFixedBossItem(FixedBossItemCreateDto dto);

    /**
     * 랜덤 보스 아이템 생성
     */
    RandomBossItem createRandomBossItem(RandomBossItemCreateDto dto);
}
