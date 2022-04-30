package com.maple.common.bossitem.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RandomBossItem extends BossItem {

    private RandomBossItemAmount amount;

    public RandomBossItem(Boss boss, Item item, RandomBossItemAmount amount) {
        checkNotNull(boss, "보스 필수입니다.");
        checkNotNull(item, "아이템은 필수입니다.");
        checkNotNull(amount, "아이템 수량은 필수입니다.");

        this.boss = boss;
        this.item = item;
        this.amount = amount;
    }
}
