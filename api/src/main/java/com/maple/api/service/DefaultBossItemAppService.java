package com.maple.api.service;

import com.maple.api.service.dto.FixedBossItemCreateDto;
import com.maple.api.service.dto.RandomBossItemCreateDto;
import com.maple.common.boss.domain.BossRepository;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.RandomBossItem;
import com.maple.common.bossitem.service.BossItemService;
import com.maple.common.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultBossItemAppService implements BossItemAppService {

    private final BossItemService bossItemService;

    private final BossRepository bossRepository;

    private final ItemRepository itemRepository;

    @Override
    public FixedBossItem createFixedBossItem(FixedBossItemCreateDto dto) {
        checkNotNull(dto, "고정 보스 아이템 dto 필수입니다.");

        val boss = bossRepository.findById(dto.bossId()).orElseThrow();
        val item = itemRepository.findById(dto.itemId()).orElseThrow();

        val fixedBossItem = new FixedBossItem(boss, item, dto.amount(), dto.price());

        return bossItemService.createFixedBossItem(fixedBossItem);
    }

    @Override
    public RandomBossItem createRandomBossItem(RandomBossItemCreateDto dto) {
        checkNotNull(dto, "랜덤 보스 아이템 dto 필수입니다.");

        val boss = bossRepository.findById(dto.bossId()).orElseThrow();
        val item = itemRepository.findById(dto.itemId()).orElseThrow();

        val randomBossItem = new RandomBossItem(boss, item, dto.amount());

        return bossItemService.createRandomBossItem(randomBossItem);
    }
}
