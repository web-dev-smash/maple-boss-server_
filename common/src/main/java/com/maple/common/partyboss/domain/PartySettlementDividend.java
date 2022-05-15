package com.maple.common.partyboss.domain;

import com.maple.common.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * 파티 정산 배당
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartySettlementDividend {

    /* 멤버 */
    @ManyToOne(fetch = FetchType.LAZY)
    private User member;

    /* 배당율 */
    @Column(columnDefinition = "numeric(3,0)")
    private BigDecimal rate;
}
