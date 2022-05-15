package com.maple.common.bossitem.service;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.service.BossService;
import com.maple.common.bossitem.domain.*;
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
import static com.maple.common.item.domain.ItemType.EXTRA;
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

    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Item item5;

    private FixedBossItem fixedBossItem1;
    private FixedBossItem fixedBossItem2;
    private FixedBossItem fixedBossItem3;
    private RandomBossItem randomBossItem1;
    private RandomBossItem randomBossItem2;

    @BeforeEach
    void setUp() {
        boss = bossService.create(createBoss());
        item = itemService.create(createItem());

        item1 = itemService.create(new Item("고정 아이템1", EXTRA));
        item2 = itemService.create(new Item("고정 아이템2", EXTRA));
        item3 = itemService.create(new Item("고정 아이템3", EXTRA));
        item4 = itemService.create(new Item("랜덤 아이템1", EXTRA));
        item5 = itemService.create(new Item("랜덤 아이템2", EXTRA));

        fixedBossItem1 = bossItemService.createFixedBossItem(createFixedBossItem(boss, item1));
        fixedBossItem2 = bossItemService.createFixedBossItem(createFixedBossItem(boss, item2));
        fixedBossItem3 = bossItemService.createFixedBossItem(createFixedBossItem(boss, item3));
        randomBossItem1 = bossItemService.createRandomBossItem(createRandomBossItem(boss, item4));
        randomBossItem2 = bossItemService.createRandomBossItem(createRandomBossItem(boss, item5));
    }

    @Test
    void 고정_보스_아이템_생성_성공() {
        val amount = new FixedBossItemAmount(1, 1);
        var fixedBossItem = new FixedBossItem(boss, item, amount, 10000L);

        fixedBossItem = bossItemService.createFixedBossItem(fixedBossItem);

        assertThat(fixedBossItem.getId()).isNotNull();

        val foundFixedBossItem = bossItemRepository.findFixedBossItem(fixedBossItem.getId()).orElseThrow();

        assertThat(foundFixedBossItem.getBoss()).isEqualTo(boss);
        assertThat(foundFixedBossItem.getItem()).isEqualTo(item);
        assertThat(foundFixedBossItem.getAmount()).isEqualTo(amount);
        assertThat(foundFixedBossItem.getMeso()).isEqualTo(10000L);
    }

    @Test
    void 고정_보스_아이템_생성_실패__아이템이_null() {
        assertThatNullPointerException().isThrownBy(() -> bossItemService.createFixedBossItem(null));
    }

    @Test
    void 랜덤_보스_아이템_생성_성공() {
        val amount = new RandomBossItemAmount(1, 1);
        var randomBossItem = new RandomBossItem(boss, item, amount);

        randomBossItem = bossItemService.createRandomBossItem(randomBossItem);

        assertThat(randomBossItem.getId()).isNotNull();

        val foundRandomBossItem = bossItemRepository.findRandomBossItem(randomBossItem.getId()).orElseThrow();

        assertThat(foundRandomBossItem.getBoss()).isEqualTo(boss);
        assertThat(foundRandomBossItem.getItem()).isEqualTo(item);
        assertThat(foundRandomBossItem.getAmount()).isEqualTo(amount);
    }

    @Test
    void 랜덤_보스_아이템_생성_실패__아이템이_null() {
        assertThatNullPointerException().isThrownBy(() -> bossItemService.createRandomBossItem(null));
    }

    @Test
    void 보스_아이템_목록_조회_성공() {
        val bossItems = bossItemService.getBossItems(boss.getId());

        assertThat(bossItems).containsExactly(fixedBossItem1, fixedBossItem2, fixedBossItem3, randomBossItem1, randomBossItem2);
    }
}
