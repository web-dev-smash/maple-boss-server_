package com.maple.common.bossitem.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.maple.common.fixture.BossItemFixture.createFixedBossAmount;
import static org.assertj.core.api.Assertions.*;

class FixedBossItemTest {

    @Test
    void 고정_보스_아이템_생성_성공() {
        val amount = createFixedBossAmount();
        val fixedBossItem = new FixedBossItem(amount, 1000);

        assertThat(fixedBossItem.getAmount()).isEqualTo(amount);
        assertThat(fixedBossItem.getPrice()).isEqualTo(1000);
    }

    @Test
    void 보스_아이템_수량이_null_이면_생성_실패() {
        assertThatNullPointerException().isThrownBy(() -> new FixedBossItem(null, 1000));
    }

    @Test
    void 보스_아이템_가격이_음수면_생성_실패() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FixedBossItem(createFixedBossAmount(), -1));
    }
}
