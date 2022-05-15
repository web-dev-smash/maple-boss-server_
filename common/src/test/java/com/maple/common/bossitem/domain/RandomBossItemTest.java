package com.maple.common.bossitem.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.maple.common.fixture.BossFixture.createBoss;
import static com.maple.common.fixture.BossItemFixture.createRandomBossItemAmount;
import static com.maple.common.fixture.ItemFixture.createItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class RandomBossItemTest {

    @Test
    void 랜덤_보스_아이템_생성_성공() {
        val boss = createBoss();
        val item = createItem();
        val amount = createRandomBossItemAmount();

        val randomBossItem = new RandomBossItem(boss, item, amount);

        assertThat(randomBossItem.getBoss()).isEqualTo(boss);
        assertThat(randomBossItem.getItem()).isEqualTo(item);
        assertThat(randomBossItem.getAmount()).isEqualTo(amount);
        assertThat(randomBossItem.getCreateAt()).isNotNull();
    }

    @Test
    void 랜덤_보스_아이템_생성_실패__보스가_null() {
        assertThatNullPointerException().isThrownBy(() -> new RandomBossItem(null, createItem(), createRandomBossItemAmount()));
    }

    @Test
    void 랜덤_보스_아이템_생성_실패__아이템이_null() {
        assertThatNullPointerException().isThrownBy(() -> new RandomBossItem(createBoss(), null, createRandomBossItemAmount()));
    }

    @Test
    void 랜덤_보스_아이템_생성_실패__수량이_null() {
        assertThatNullPointerException().isThrownBy(() -> new RandomBossItem(createBoss(), createItem(), null));
    }
}