package com.maple.common.bossitem.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.item.domain.Item;
import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_boss_item", columnNames = {"boss_id", "item_id"})
        }
)
@Inheritance
public abstract class BossItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Boss boss;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private OffsetDateTime createAt = OffsetDateTime.now();
}
