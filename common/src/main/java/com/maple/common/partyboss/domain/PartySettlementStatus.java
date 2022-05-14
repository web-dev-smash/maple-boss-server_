package com.maple.common.partyboss.domain;

/**
 * 파티 정산 상태
 * <p><p>
 * 이동가능한 상태
 * <p>
 * CREATE -> IN_PROGRESS
 * <p>
 * IN_PROGRESS -> CONFIRMED, CANCELED
 * <p>
 * CONFIRMED -> IN_PROGRESS, MANUAL_SETTLED, AUTO_SETTLED
 * <p>
 */
public enum PartySettlementStatus {
    CREATED, //생성됨
    IN_PROGRESS, //정산 진행중
    CONFIRMED, // 정산 확정됨
    MANUAL_SETTLED, // 수동 정산됨 (사용자가 직접 정산 처리)
    AUTO_SETTLED, // 자동 정산됨 (시스템에서 주기적으로 정산 처리)
    CANCELED // 정산 취소됨
}
