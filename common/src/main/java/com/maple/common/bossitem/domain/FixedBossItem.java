package com.maple.common.bossitem.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.item.domain.Item;
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
    private FixedBossItemAmount amount;

    /* 아이템 가격 */
    private long meso;

    public FixedBossItem(Boss boss, Item item, FixedBossItemAmount amount, long meso) {
        checkNotNull(boss, "보스 필수입니다.");
        checkNotNull(item, "아이템은 필수입니다.");
        checkNotNull(amount, "아이템 수량은 필수입니다.");

        checkArgument(meso > -1, "아이템 가격은 음수일 수 없습니다.");

        this.boss = boss;
        this.item = item;
        this.amount = amount;
        this.meso = meso;
    }
}
