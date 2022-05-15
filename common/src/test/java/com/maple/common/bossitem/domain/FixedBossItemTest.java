package com.maple.common.bossitem.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.maple.common.fixture.BossFixture.createBoss;
import static com.maple.common.fixture.BossItemFixture.createFixedBossAmount;
import static com.maple.common.fixture.ItemFixture.createItem;
import static org.assertj.core.api.Assertions.*;

class FixedBossItemTest {

    @Test
    void 고정_보스_아이템_생성_성공() {
        val boss = createBoss();
        val item = createItem();
        val amount = createFixedBossAmount();

        val fixedBossItem = new FixedBossItem(boss, item, amount, 1000);

        assertThat(fixedBossItem.getBoss()).isEqualTo(boss);
        assertThat(fixedBossItem.getItem()).isEqualTo(item);
        assertThat(fixedBossItem.getAmount()).isEqualTo(amount);
        assertThat(fixedBossItem.getMeso()).isEqualTo(1000);
    }

    @Test
    void 고정_보스_아이템_수량이_null_이면_생성_실패() {
        assertThatNullPointerException().isThrownBy(() -> new FixedBossItem(createBoss(), createItem(), null, 1000));
    }

    @Test
    void 고정_보스_아이템_가격이_음수면_생성_실패() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FixedBossItem(createBoss(), createItem(), createFixedBossAmount(), -1));
    }
}
