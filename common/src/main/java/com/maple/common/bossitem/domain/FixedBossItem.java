package com.maple.common.bossitem.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 고정 보스 아이템
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FixedBossItem extends BossItem {

    /* 아이템 수량 */
    private FixedBossAmount amount;

    /* 아이템 가격 */
    private long price;

    public FixedBossItem(FixedBossAmount amount, long price) {
        checkNotNull(amount, "아이템 수량은 필수입니다.");

        checkArgument(price > -1, "아이템 가격은 음수일 수 없습니다.");

        this.amount = amount;
        this.price = price;
    }
}
