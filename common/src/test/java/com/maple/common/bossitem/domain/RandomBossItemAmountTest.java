package com.maple.common.bossitem.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.maple.common.bossitem.domain.RandomBossItemAmount.ALLOWED_MAXIMUM_AMOUNT;
import static com.maple.common.bossitem.domain.RandomBossItemAmount.ALLOWED_MINIMUM_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RandomBossItemAmountTest {

    @Test
    void 랜덤_보스_아이템_수량_생성_성공() {
        val randomBossAmount = new RandomBossItemAmount(ALLOWED_MINIMUM_AMOUNT, ALLOWED_MINIMUM_AMOUNT);

        assertThat(randomBossAmount.getMinimum()).isEqualTo(ALLOWED_MINIMUM_AMOUNT);
        assertThat(randomBossAmount.getMaximum()).isEqualTo(ALLOWED_MINIMUM_AMOUNT);
    }

    @Test
    void 랜덤_보스_아이템_수량_생성_실패__랜덤_보스_아이템_수량이_허용된_최소수량_미만() {
        assertThatIllegalArgumentException().isThrownBy(() -> new RandomBossItemAmount(ALLOWED_MINIMUM_AMOUNT - 1, ALLOWED_MINIMUM_AMOUNT));
    }

    @Test
    void 랜덤_보스_아이템_수량_생성_실패__랜덤_보스_아이템_수량이_허용된_최대수량_초과() {
        assertThatIllegalArgumentException().isThrownBy(() -> new RandomBossItemAmount(ALLOWED_MINIMUM_AMOUNT, ALLOWED_MAXIMUM_AMOUNT + 1));
    }
}
