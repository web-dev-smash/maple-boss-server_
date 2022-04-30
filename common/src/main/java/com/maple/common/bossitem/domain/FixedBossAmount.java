package com.maple.common.bossitem.domain;

import com.google.common.primitives.Ints;
import lombok.Getter;

import javax.persistence.Embeddable;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 보스 아이템 수량
 */
@Getter
@Embeddable
public class FixedBossAmount {

    public static final int ALLOWED_MINIMUM_AMOUNT = 1;

    /* 최소 수량 */
    private int minimum;

    /* 최대 수량 */
    private int maximum;

    public FixedBossAmount(int minimum, int maximum) {
        checkArgument(Ints.min(minimum, maximum) >= ALLOWED_MINIMUM_AMOUNT, "최소 수량과 최대 수량은 최소 {0}개 이상이여야 합니다.", ALLOWED_MINIMUM_AMOUNT);
        checkArgument(minimum <= maximum, "최대 수량이 최소 수량 보다 적을 수 없습니다.");

        this.minimum = minimum;
        this.maximum = maximum;
    }
}
