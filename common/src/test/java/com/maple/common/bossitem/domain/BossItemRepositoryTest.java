package com.maple.common.bossitem.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import com.maple.common.boss.domain.BossRepository;
import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemRepository;
import com.maple.common.support.BaseRepositoryTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.maple.common.fixture.BossFixture.createBoss;
import static com.maple.common.fixture.BossItemFixture.createFixedBossItem;
import static com.maple.common.fixture.BossItemFixture.createRandomBossItem;
import static com.maple.common.fixture.ItemFixture.createItem;
import static org.assertj.core.api.Assertions.assertThat;

class BossItemRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BossItemRepository bossItemRepository;

    private Boss boss;
    private Boss otherBoss;
    private Item randomItem;
    private Item fixedItem;

    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Item item5;
    private Item item6;

    private RandomBossItem randomBossItem1;
    private RandomBossItem randomBossItem2;
    private RandomBossItem randomBossItem3;
    private FixedBossItem fixedBossItem1;
    private FixedBossItem fixedBossItem2;
    private FixedBossItem fixedBossItem3;

    private FixedBossItem otherBossItem1;
    private RandomBossItem otherBossItem2;

    @BeforeEach
    void setUp() {
        boss = bossRepository.save(createBoss());
        otherBoss = bossRepository.save(new Boss("다른보스", 1, BossClass.EASY, 1, 10, 1L, 2L, 3L, 4L, 1, 1));

        randomItem = itemRepository.save(createItem());
        fixedItem = itemRepository.save(createItem());
    }

    @Test
    void 보스_아이템_등록_성공() {
        val randomBossItem = bossItemRepository.save(createRandomBossItem(boss, randomItem));
        val fixedBossItem = bossItemRepository.save(createFixedBossItem(boss, fixedItem));

        val foundBossItems = bossItemRepository.findAll();

        assertThat(foundBossItems).containsExactly(randomBossItem, fixedBossItem);
    }

    @Test
    void 보스_아이템_전체_조회_성공() {
        보스_아이팀_세팅();

        val bossItems = bossItemRepository.findAll(boss);

        assertThat(bossItems).containsExactly(randomBossItem1, randomBossItem2, randomBossItem3, fixedBossItem1, fixedBossItem2, fixedBossItem3);
        assertThat(bossItems).doesNotContain(otherBossItem1, otherBossItem2);
    }

    @Test
    void 고정_보스_아이템_목록_조회_성공() {
        보스_아이팀_세팅();

        val foundFixedBossItems = bossItemRepository.findAllFixedBossItem(boss);

        assertThat(foundFixedBossItems).containsExactly(fixedBossItem1, fixedBossItem2, fixedBossItem3);
    }

    @Test
    void 랜덤_보스_아이템_목록_조회_성공() {
        보스_아이팀_세팅();

        val foundRandomBossItems = bossItemRepository.findAllRandomBossItem(boss);

        assertThat(foundRandomBossItems).containsExactly(randomBossItem1, randomBossItem2, randomBossItem3);
    }

    private void 보스_아이팀_세팅() {
        item1 = itemRepository.save(createItem());
        item2 = itemRepository.save(createItem());
        item3 = itemRepository.save(createItem());
        item4 = itemRepository.save(createItem());
        item5 = itemRepository.save(createItem());
        item6 = itemRepository.save(createItem());

        randomBossItem1 = bossItemRepository.save(createRandomBossItem(boss, item1));
        randomBossItem2 = bossItemRepository.save(createRandomBossItem(boss, item2));
        randomBossItem3 = bossItemRepository.save(createRandomBossItem(boss, item3));
        fixedBossItem1 = bossItemRepository.save(createFixedBossItem(boss, item4));
        fixedBossItem2 = bossItemRepository.save(createFixedBossItem(boss, item5));
        fixedBossItem3 = bossItemRepository.save(createFixedBossItem(boss, item6));

        otherBossItem1 = bossItemRepository.save(createFixedBossItem(otherBoss, item1));
        otherBossItem2 = bossItemRepository.save(createRandomBossItem(otherBoss, item4));
    }

    @Test
    void 고정_보스_아이템_조회_성공() {
        val randomBossItem = bossItemRepository.save(createRandomBossItem(boss, randomItem));
        val fixedBossItem = bossItemRepository.save(createFixedBossItem(boss, fixedItem));

        val foundFixedBossItem = bossItemRepository.findFixedBossItem(fixedBossItem.getId()).orElseThrow();
        val notFoundBossItem = bossItemRepository.findFixedBossItem(randomBossItem.getId());

        assertThat(foundFixedBossItem.getId()).isEqualTo(fixedBossItem.getId());
        assertThat(notFoundBossItem.isPresent()).isFalse();
    }

    @Test
    void 랜덤_보스_아이템_조회_성공() {
        val randomBossItem = bossItemRepository.save(createRandomBossItem(boss, randomItem));
        val fixedBossItem = bossItemRepository.save(createFixedBossItem(boss, fixedItem));

        val foundRandomBossItem = bossItemRepository.findRandomBossItem(randomBossItem.getId()).orElseThrow();
        val notFoundBossItem = bossItemRepository.findRandomBossItem(fixedBossItem.getId());

        assertThat(foundRandomBossItem.getId()).isEqualTo(randomBossItem.getId());
        assertThat(notFoundBossItem.isPresent()).isFalse();
    }
}
