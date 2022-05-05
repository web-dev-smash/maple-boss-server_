package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;

public class BossFindDto {

    public record BossFindResponse(BossFindData boss) {
    }

    public record BossFindData(
            long id,
            String name,
            int level,
            BossClass clazz,
            int entryMinLevel,
            int entryMaxLevel,
            Long hpPhaseOne,
            Long hpPhaseTwo,
            Long hpPhaseThree,
            Long hpPhaseFour,
            Long totalHpPhase,
            int arcaneForce,
            int deathLimit
    ) {

        public static BossFindData create(Boss boss) {
            return new BossFindData(
                    boss.getId(), boss.getName(), boss.getLevel(), boss.getClazz(), boss.getEntryMinLevel(), boss.getEntryMaxLevel(),
                    boss.getHpPhaseOne(), boss.getHpPhaseTwo(), boss.getHpPhaseThree(), boss.getHpPhaseFour(), boss.totalHpPhase(),
                    boss.getArcaneForce(), boss.getDeathLimit()
            );
        }
    }
}
