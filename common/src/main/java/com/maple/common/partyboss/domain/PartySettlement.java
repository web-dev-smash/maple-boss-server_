package com.maple.common.partyboss.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.party.domain.Party;
import com.maple.common.support.BaseEntity;
import com.maple.common.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * 파티 정산
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartySettlement extends BaseEntity {

    /* 파티 */
    @ManyToOne(fetch = FetchType.LAZY)
    private Party party;

    /* 보스 */
    @ManyToOne(fetch = FetchType.LAZY)
    private Boss boss;

    /* 정산 아이템 */
    @ElementCollection
    private List<PartySettlementItem> items;

    /* 멤버별 배당율 */
    @ElementCollection
    private List<PartySettlementDividend> dividends;

    /* 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PartySettlementStatus status = PartySettlementStatus.CREATED;

    /* 생성일 */
    private final OffsetDateTime createAt = OffsetDateTime.now();

    public PartySettlement(Party party, Boss boss, List<PartySettlementItem> items, List<PartySettlementDividend> dividends) {
        this.party = party;
        this.boss = boss;
        this.items = items;
        this.dividends = dividends;
    }

    /**
     * 시스템 자동 정산
     */
    private void settleByAuto() {
    }

    /**
     * 유저 수동 정산
     */
    private void settleByManual() {
    }

    /**
     * 정산 취소
     */
    private void cancel() {
    }

    /**
     * 보스 판매액 배당
     */
    private void divide() {
    }

    /**
     * 멤버 배당율 변경
     */
    private void changeDividendRate(User member, BigDecimal rate) {
    }

    /**
     * 판매 총액
     */
    private long sumMeso() {
        throw new RuntimeException("미구현");
    }
}
