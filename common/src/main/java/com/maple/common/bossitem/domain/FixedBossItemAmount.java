package com.maple.common.bossitem.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;

import static com.maple.core.exception.Preconditions.require;
import static java.util.Collections.min;

/**
 * 보스 아이템 수량
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FixedBossItemAmount {

    /* 수량의 최대 값 */
    public static final int ALLOWED_MINIMUM_AMOUNT = 1;

    /* 최소 수량 */
    private int minimum;

    /* 최대 수량 */
    private int maximum;

    public FixedBossItemAmount(int minimum, int maximum) {
        require(min(List.of(minimum, maximum)) >= ALLOWED_MINIMUM_AMOUNT, "최소 수량과 최대 수량은 최소 {0}개 이상이여야 합니다.", ALLOWED_MINIMUM_AMOUNT);
        require(minimum <= maximum, "최대 수량이 최소 수량 보다 적을 수 없습니다.");

        this.minimum = minimum;
        this.maximum = maximum;
    }
}
