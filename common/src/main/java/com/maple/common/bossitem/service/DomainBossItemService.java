package com.maple.common.bossitem.service;

import com.maple.common.bossitem.domain.BossItemRepository;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.RandomBossItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainBossItemService implements BossItemService {

    private final BossItemRepository bossItemRepository;

    @Override
    public FixedBossItem createFixedBossItem(FixedBossItem fixedBossItem) {
        checkNotNull(fixedBossItem, "고정 보스 아이템은 필수입니다");

        return bossItemRepository.save(fixedBossItem);
    }

    @Override
    public RandomBossItem createRandomBossItem(RandomBossItem randomBossItem) {
        checkNotNull(randomBossItem, "랜덤 보스 아이템은 필수입니다.");

        return bossItemRepository.save(randomBossItem);
    }
}
