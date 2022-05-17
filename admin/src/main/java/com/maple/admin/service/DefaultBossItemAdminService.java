package com.maple.admin.service;

import com.maple.admin.service.dto.FixedBossItemCreateDto;
import com.maple.admin.service.dto.RandomBossItemCreateDto;
import com.maple.common.boss.domain.BossRepository;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.RandomBossItem;
import com.maple.common.bossitem.service.BossItemService;
import com.maple.common.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.maple.core.exception.Preconditions.notNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultBossItemAdminService implements BossItemAdminService {

    private final BossItemService bossItemService;

    private final BossRepository bossRepository;

    private final ItemRepository itemRepository;

    @Override
    public FixedBossItem createFixedBossItem(FixedBossItemCreateDto dto) {
        notNull(dto);

        val boss = bossRepository.findById(dto.getBossId()).orElseThrow();
        val item = itemRepository.findById(dto.getItemId()).orElseThrow();

        val fixedBossItem = new FixedBossItem(boss, item, dto.getAmount(), dto.getPrice());

        return bossItemService.createFixedBossItem(fixedBossItem);
    }

    @Override
    public RandomBossItem createRandomBossItem(RandomBossItemCreateDto dto) {
        notNull(dto);

        val boss = bossRepository.findById(dto.getBossId()).orElseThrow();
        val item = itemRepository.findById(dto.getItemId()).orElseThrow();

        val randomBossItem = new RandomBossItem(boss, item, dto.getAmount());

        return bossItemService.createRandomBossItem(randomBossItem);
    }
}
