package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;

import java.util.List;

public class BossGetAllDto {

    public record BossGetAllResponse(List<BossGetAllData> boss) {
    }

    public record BossGetAllData(
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

        private BossGetAllData(Boss boss) {
            this(
                    boss.getId(), boss.getName(), boss.getLevel(), boss.getClazz(), boss.getEntryMinLevel(), boss.getEntryMaxLevel(),
                    boss.getHpPhaseOne(), boss.getHpPhaseTwo(), boss.getHpPhaseThree(), boss.getHpPhaseFour(), boss.totalHpPhase(),
                    boss.getArcaneForce(), boss.getDeathLimit()
            );
        }

        public static List<BossGetAllData> create(List<Boss> bosses) {
            return bosses.stream().map(BossGetAllData::new).toList();
        }
    }
}
