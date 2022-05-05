package com.maple.common.bossitem.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import com.maple.common.boss.domain.BossRepository;
import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.maple.common.fixture.BossFixture.createBoss;
import static com.maple.common.fixture.BossItemFixture.createFixedBossItem;
import static com.maple.common.fixture.BossItemFixture.createRandomBossItem;
import static com.maple.common.fixture.ItemFixture.createItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class BossItemRepositoryTest {

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BossItemRepository bossItemRepository;

    private Boss boss;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Item item5;

    @BeforeEach
    void setUp() {
        boss = bossRepository.save(createBoss());
        item1 = itemRepository.save(createItem());
        item2 = itemRepository.save(createItem());
        item3 = itemRepository.save(createItem());
        item4 = itemRepository.save(createItem());
        item5 = itemRepository.save(createItem());
    }

    @Test
    void 보스_아이템_등록_성공() {
        val randomBossItem = createRandomBossItem(boss, item1);
        val fixedBossItem = createFixedBossItem(boss, item2);

        bossItemRepository.saveAll(List.of(randomBossItem, fixedBossItem));

        val foundBossItems = bossItemRepository.findAll();

        assertThat(foundBossItems).containsExactly(randomBossItem, fixedBossItem);
    }

    @Test
    void 보스_아이템_전체조회() {
        val randomBossItem1 = createRandomBossItem(boss, item1);
        val randomBossItem2 = createRandomBossItem(boss, item2);

        val fixedBossItem1 = createFixedBossItem(boss, item3);
        val fixedBossItem2 = createFixedBossItem(boss, item4);
        val fixedBossItem3 = createFixedBossItem(boss, item5);

        var otherBoss = new Boss("다른보스", 1, BossClass.EASY, 1, 10, 1L, 2L, 3L, 4L, 1, 1);

        otherBoss = bossRepository.save(otherBoss);

        val otherBossItem1 = bossItemRepository.save(createFixedBossItem(otherBoss, item1));
        val otherBossItem2 = bossItemRepository.save(createRandomBossItem(otherBoss, item4));

        bossItemRepository.saveAll(List.of(
                randomBossItem1, randomBossItem2, fixedBossItem1, fixedBossItem2, fixedBossItem3,
                otherBossItem1, otherBossItem2
        ));

        val bossItems = bossItemRepository.findAll(boss);

        assertThat(bossItems).containsExactly(randomBossItem1, randomBossItem2, fixedBossItem1, fixedBossItem2, fixedBossItem3);
        assertThat(bossItems).doesNotContain(otherBossItem1, otherBossItem2);
    }

    @Test
    void 고정_보스_아이템_전체조회() {
        val randomBossItem1 = createRandomBossItem(boss, item1);
        val randomBossItem2 = createRandomBossItem(boss, item2);

        val fixedBossItem1 = createFixedBossItem(boss, item3);
        val fixedBossItem2 = createFixedBossItem(boss, item4);
        val fixedBossItem3 = createFixedBossItem(boss, item5);

        bossItemRepository.saveAll(List.of(
                randomBossItem1, randomBossItem2,
                fixedBossItem1, fixedBossItem2, fixedBossItem3
        ));

        val foundFixedBossItems = bossItemRepository.findAllFixedBossItem();

        assertThat(foundFixedBossItems).containsExactly(fixedBossItem1, fixedBossItem2, fixedBossItem3);
    }

    @Test
    void 랜덤_보스_아이템_전체조회() {
        val randomBossItem1 = createRandomBossItem(boss, item1);
        val randomBossItem2 = createRandomBossItem(boss, item2);
        val randomBossItem3 = createRandomBossItem(boss, item3);

        val fixedBossItem1 = createFixedBossItem(boss, item4);
        val fixedBossItem2 = createFixedBossItem(boss, item5);

        bossItemRepository.saveAll(List.of(
                randomBossItem1, randomBossItem2, randomBossItem3,
                fixedBossItem1, fixedBossItem2
        ));

        val foundRandomBossItems = bossItemRepository.findAllRandomBossItem();

        assertThat(foundRandomBossItems).containsExactly(randomBossItem1, randomBossItem2, randomBossItem3);
    }

    @Test
    void 고정_보스_아이템_조회() {
        var fixedBossItem = createFixedBossItem(boss, item1);
        var randomBossItem = createRandomBossItem(boss, item2);

        fixedBossItem = bossItemRepository.save(fixedBossItem);
        randomBossItem = bossItemRepository.save(randomBossItem);

        assertThat(fixedBossItem.getId()).isNotNull();

        val foundFixedBossItem = bossItemRepository.findFixedBossItem(fixedBossItem.getId()).orElseThrow();
        val notFoundBossItem = bossItemRepository.findFixedBossItem(randomBossItem.getId());

        assertThat(foundFixedBossItem.getId()).isEqualTo(fixedBossItem.getId());
        assertThat(notFoundBossItem.isPresent()).isFalse();
    }

    @Test
    void 랜덤_보스_아이템_조회() {
        var randomBossItem = createRandomBossItem(boss, item1);
        var fixedBossItem = createFixedBossItem(boss, item2);

        randomBossItem = bossItemRepository.save(randomBossItem);
        fixedBossItem = bossItemRepository.save(fixedBossItem);

        assertThat(randomBossItem.getId()).isNotNull();

        val foundRandomBossItem = bossItemRepository.findRandomBossItem(randomBossItem.getId()).orElseThrow();
        val notFoundBossItem = bossItemRepository.findRandomBossItem(fixedBossItem.getId());

        assertThat(foundRandomBossItem.getId()).isEqualTo(randomBossItem.getId());
        assertThat(notFoundBossItem.isPresent()).isFalse();
    }
}
