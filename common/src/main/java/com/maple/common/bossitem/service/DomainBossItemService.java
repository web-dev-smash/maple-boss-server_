package com.maple.common.bossitem.service;

import com.maple.common.boss.domain.BossRepository;
import com.maple.common.bossitem.domain.BossItem;
import com.maple.common.bossitem.domain.BossItemRepository;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.RandomBossItem;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.maple.core.exception.Preconditions.notNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainBossItemService implements BossItemService {

    private final BossItemRepository bossItemRepository;

    private final BossRepository bossRepository;

    @Override
    public FixedBossItem createFixedBossItem(FixedBossItem fixedBossItem) {
        notNull(fixedBossItem);

        return bossItemRepository.save(fixedBossItem);
    }

    @Override
    public RandomBossItem createRandomBossItem(RandomBossItem randomBossItem) {
        notNull(randomBossItem);

        return bossItemRepository.save(randomBossItem);
    }

    @Override
    public List<BossItem> getBossItems(long bossId) {
        val boss = bossRepository.getById(bossId);

        return bossItemRepository.findAll(boss);
    }
}
