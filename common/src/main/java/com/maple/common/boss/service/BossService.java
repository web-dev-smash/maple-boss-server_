package com.maple.common.boss.service;

import com.maple.common.boss.domain.Boss;

public interface BossService {

    /**
     * 보스 생성
     */
    Boss create(Boss boss);

    /**
     * 보스 상세 조회
     */
    Boss getBoss(Long id);
}
