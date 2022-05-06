package com.maple.common.bossitem.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RandomBossItemAmount {

    public static final int ALLOWED_MINIMUM_AMOUNT = 1;
    public static final int ALLOWED_MAXIMUM_AMOUNT = 2;

    /* 최소 수량 */
    private int minimum;

    /* 최대 수량 */
    private int maximum;

    public RandomBossItemAmount(int minimum, int maximum) {
        checkArgument(minimum >= ALLOWED_MINIMUM_AMOUNT, "최소 수량은 최소 [0]개 이상이어야 합니다.", ALLOWED_MINIMUM_AMOUNT);
        checkArgument(maximum <= ALLOWED_MINIMUM_AMOUNT, "최대 수량은 최대 [0]개 미만이어야 합니다.", ALLOWED_MAXIMUM_AMOUNT);

        this.minimum = minimum;
        this.maximum = maximum;
    }
}
