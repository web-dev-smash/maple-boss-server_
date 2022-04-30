package com.maple.api.controller.dto;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class BossCreateDto {

    @Getter
    @AllArgsConstructor
    public static class BossCreateRequest {

        private String name;
        private int level;
        private BossClass clazz;
        private int entryMinLevel;
        private int entryMaxLevel;
        private Long hpPhaseOne;
        private Long hpPhaseTwo;
        private Long hpPhaseThree;
        private Long hpPhaseFour;
        private int arcaneForce;
        private int deathLimit;

        public Boss toEntity() {
            return new Boss(name, level, clazz, entryMinLevel, entryMaxLevel, hpPhaseOne, hpPhaseTwo, hpPhaseThree, hpPhaseFour, arcaneForce, deathLimit);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class BossCreateResponse {

        private BossCreateData boss;
    }

    @Getter
    @AllArgsConstructor
    public static class BossCreateData {

        private long id;
        private String name;
        private int level;
        private BossClass clazz;
        private int entryMinLevel;
        private int entryMaxLevel;
        private Long hpPhaseOne;
        private Long hpPhaseTwo;
        private Long hpPhaseThree;
        private Long hpPhaseFour;
        private Long totalHpPhase;
        private int arcaneForce;
        private int deathLimit;

        public static BossCreateData create(Boss boss) {
            return new BossCreateData(
                    boss.getId(), boss.getName(), boss.getLevel(), boss.getClazz(), boss.getEntryMinLevel(), boss.getEntryMaxLevel(),
                    boss.getHpPhaseOne(), boss.getHpPhaseTwo(), boss.getHpPhaseThree(), boss.getHpPhaseFour(), boss.totalHpPhase(),
                    boss.getArcaneForce(), boss.getDeathLimit()
            );
        }
    }
}
