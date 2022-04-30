package com.maple.common.bossitem.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.item.domain.Item;
import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * 보스 아이템
 */
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

    /* 보스 */
    @ManyToOne(fetch = FetchType.LAZY)
    protected Boss boss;

    /* 이이템 */
    @ManyToOne(fetch = FetchType.LAZY)
    protected Item item;

    /* 생성일 */
    private OffsetDateTime createAt = OffsetDateTime.now();
}
