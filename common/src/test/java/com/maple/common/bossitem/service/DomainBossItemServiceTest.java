package com.maple.common.bossitem.service;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.service.BossService;
import com.maple.common.bossitem.domain.BossItemRepository;
import com.maple.common.item.domain.Item;
import com.maple.common.item.service.ItemService;
import com.maple.common.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.maple.common.fixture.BossFixture.createBoss;
import static com.maple.common.fixture.BossItemFixture.createFixedBossItem;
import static com.maple.common.fixture.BossItemFixture.createRandomBossItem;
import static com.maple.common.fixture.ItemFixture.createItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;


class DomainBossItemServiceTest extends BaseServiceTest {

    @Autowired
    private BossItemService bossItemService;

    @Autowired
    private BossService bossService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private BossItemRepository bossItemRepository;

    private Boss boss;
    private Item item;

    @BeforeEach
    void setUp() {
        boss = bossService.create(createBoss());
        item = itemService.create(createItem());
    }

    @Test
    void 고정_보스_아이템_생성_성공() {
        var fixedBossItem = createFixedBossItem(boss, item);

        fixedBossItem = bossItemService.createFixedBossItem(fixedBossItem);

        val foundFixedBossItem = bossItemRepository.findFixedBossItem(fixedBossItem.getId()).orElseThrow();

        assertThat(foundFixedBossItem.getAmount()).isEqualTo(fixedBossItem.getAmount());
        assertThat(foundFixedBossItem.getPrice()).isEqualTo(fixedBossItem.getPrice());
    }

    @Test
    void 고정_보스_아이템이_null_이면_생성_실패() {
        assertThatNullPointerException().isThrownBy(()-> bossItemService.createFixedBossItem(null));
    }

    @Test
    void 랜덤_보스_아이템_생성_성공() {
        var randomBossItem = createRandomBossItem(boss, item);

        randomBossItem = bossItemService.createRandomBossItem(randomBossItem);

        val foundRandomBossItem = bossItemRepository.findRandomBossItem(randomBossItem.getId()).orElseThrow();

        assertThat(foundRandomBossItem.getAmount()).isEqualTo(randomBossItem.getAmount());
    }

    @Test
    void 랜덤_보스_아이템이_null_이면_생성_실패() {
        assertThatNullPointerException().isThrownBy(()-> bossItemService.createRandomBossItem(null));
    }
}
