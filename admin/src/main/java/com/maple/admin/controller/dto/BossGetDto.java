package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;

import java.util.List;

public class BossGetDto {

    public record BossesGetResponse(List<BossGetData> boss) {
    }

    public record BossGetResponse(BossGetData boss) {
    }

    public record BossGetData(
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

        public static BossGetData create(Boss boss) {
            return new BossGetData(
                    boss.getId(), boss.getName(), boss.getLevel(), boss.getClazz(), boss.getEntryMinLevel(), boss.getEntryMaxLevel(),
                    boss.getHpPhaseOne(), boss.getHpPhaseTwo(), boss.getHpPhaseThree(), boss.getHpPhaseFour(), boss.totalHpPhase(),
                    boss.getArcaneForce(), boss.getDeathLimit()
            );
        }

        public static List<BossGetData> create(List<Boss> bosses) {
            return bosses.stream()
                    .map(BossGetData::create)
                    .toList();
        }
    }
}
