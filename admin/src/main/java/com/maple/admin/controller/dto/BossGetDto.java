package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class BossGetDto {

    @Getter
    @AllArgsConstructor
    public static class BossGetResponse{
        private BossGetData boss;
    }

    @Getter
    @AllArgsConstructor
    public static class BossGetData{
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

        public static BossGetData create(Boss boss) {
            return new BossGetData(
                    boss.getId(), boss.getName(), boss.getLevel(), boss.getClazz(),
                    boss.getEntryMinLevel(), boss.getEntryMaxLevel(), boss.getHpPhaseOne(),
                    boss.getHpPhaseTwo(), boss.getHpPhaseThree(), boss.getHpPhaseFour(), boss.totalHpPhase(),
                    boss.getArcaneForce(), boss.getDeathLimit()
            );
        }
    }
}
