package com.maple.common.bossitem.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.maple.common.bossitem.domain.FixedBossItemAmount.ALLOWED_MINIMUM_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FixedBossItemAmountTest {

    @Test
    void 고정_보스_아이템_수량_생성_성공() {
        val fixedBossAmount = new FixedBossItemAmount(ALLOWED_MINIMUM_AMOUNT, ALLOWED_MINIMUM_AMOUNT);

        assertThat(fixedBossAmount.getMinimum()).isEqualTo(ALLOWED_MINIMUM_AMOUNT);
        assertThat(fixedBossAmount.getMaximum()).isEqualTo(ALLOWED_MINIMUM_AMOUNT);
    }

    @Test
    void 고정_보스_아이템_수량_생성_실패__고정_보스_아이템_수량이_허용된_최소수량_미만() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FixedBossItemAmount(ALLOWED_MINIMUM_AMOUNT - 1, ALLOWED_MINIMUM_AMOUNT));
        assertThatIllegalArgumentException().isThrownBy(() -> new FixedBossItemAmount(ALLOWED_MINIMUM_AMOUNT, ALLOWED_MINIMUM_AMOUNT - 1));
    }

    @Test
    void 고정_보스_아이템_수량_생성_실패__고정_보스_아이템_최대수량이_최소수량보다_적으면() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FixedBossItemAmount(ALLOWED_MINIMUM_AMOUNT + 1, ALLOWED_MINIMUM_AMOUNT));
    }
}