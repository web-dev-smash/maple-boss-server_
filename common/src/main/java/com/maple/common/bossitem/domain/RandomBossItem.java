package com.maple.common.bossitem.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import static com.maple.core.exception.Preconditions.notNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RandomBossItem extends BossItem {

    private RandomBossItemAmount amount;

    public RandomBossItem(Boss boss, Item item, RandomBossItemAmount amount) {
        notNull(boss);
        notNull(item);
        notNull(amount);

        this.boss = boss;
        this.item = item;
        this.amount = amount;
    }
}
