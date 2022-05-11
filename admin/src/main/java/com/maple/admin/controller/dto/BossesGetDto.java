package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;

import java.util.List;

public class BossesGetDto {

    public record BossesGetResponse(List<BossesGetData> boss) {
    }

    public record BossesGetData(
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

        public static List<BossesGetData> create(List<Boss> bosses) {
            return bosses.stream()
                    .map(BossesGetData::create)
                    .toList();
        }

        private static BossesGetData create(Boss boss) {
            return new BossesGetData(
                    boss.getId(), boss.getName(), boss.getLevel(), boss.getClazz(), boss.getEntryMinLevel(), boss.getEntryMaxLevel(),
                    boss.getHpPhaseOne(), boss.getHpPhaseTwo(), boss.getHpPhaseThree(), boss.getHpPhaseFour(), boss.totalHpPhase(),
                    boss.getArcaneForce(), boss.getDeathLimit()
            );
        }
    }
}
