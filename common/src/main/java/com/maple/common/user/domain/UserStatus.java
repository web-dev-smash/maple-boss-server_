package com.maple.common.user.domain;

import java.util.EnumSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public enum UserStatus {
    CREATED, //생성됨
    ACTIVATED, //활성화 됨 (이메일 인증 완료)
    INACTIVATING, //비활성화 대기중 (계정 복구가 이메일로 가능한 상태)
    INACTIVATED; //비활성화 됨 (계정 복구가 불가능한 상태)

    public static final Set<UserStatus> CAN_MOVE_TO_ACTIVATED = unmodifiableSet(EnumSet.of(CREATED, INACTIVATING));
    public static final Set<UserStatus> CAN_MOVE_TO_INACTIVATING = unmodifiableSet(EnumSet.of(ACTIVATED));
    public static final Set<UserStatus> CAN_MOVE_TO_INACTIVATED = unmodifiableSet(EnumSet.of(INACTIVATING));
}
