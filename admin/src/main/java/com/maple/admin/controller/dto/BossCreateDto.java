package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;

public class BossCreateDto {

    public record BossCreateRequest(
            String name,
            int level,
            BossClass clazz,
            int entryMinLevel,
            int entryMaxLevel,
            Long hpPhaseOne,
            Long hpPhaseTwo,
            Long hpPhaseThree,
            Long hpPhaseFour,
            int arcaneForce,
            int deathLimit
    ) {

        public Boss toEntity() {
            return new Boss(name, level, clazz, entryMinLevel, entryMaxLevel, hpPhaseOne, hpPhaseTwo, hpPhaseThree, hpPhaseFour, arcaneForce, deathLimit);
        }
    }

    public record BossCreateResponse(BossCreateData boss) {
    }

    public record BossCreateData(
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

        public static BossCreateData create(Boss boss) {
            return new BossCreateData(
                    boss.getId(), boss.getName(), boss.getLevel(), boss.getClazz(), boss.getEntryMinLevel(), boss.getEntryMaxLevel(),
                    boss.getHpPhaseOne(), boss.getHpPhaseTwo(), boss.getHpPhaseThree(), boss.getHpPhaseFour(), boss.totalHpPhase(),
                    boss.getArcaneForce(), boss.getDeathLimit()
            );
        }
    }
}
