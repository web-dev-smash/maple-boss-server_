package com.maple.common.partyboss.domain;

import com.maple.common.bossitem.domain.RandomBossItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * 파티 정산 아이템
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartySettlementItem {

    /* 랜덤 보스 아이템 */
    @ManyToOne(fetch = FetchType.LAZY)
    private RandomBossItem randomBossItem;

    /* 수량 */
    private int amount;

    /* 판매된 금액 */
    private long meso;
}
