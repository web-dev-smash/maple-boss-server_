package com.maple.admin.controller.dto;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BossGetAllDto {

    @Getter
    @AllArgsConstructor
    public static class BossGetAllResponse{
        private List<BossGetAllData> boss;
    }

    @Getter
    @AllArgsConstructor
    public static class BossGetAllData{
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

        private BossGetAllData(Boss boss){
            this(
                    boss.getId(), boss.getName(), boss.getLevel(), boss.getClazz(), boss.getEntryMinLevel(),
                    boss.getEntryMaxLevel(), boss.getHpPhaseOne(), boss.getHpPhaseTwo(), boss.getHpPhaseThree(),
                    boss.getHpPhaseFour(), boss.totalHpPhase(), boss.getArcaneForce(), boss.getDeathLimit()
            );
        }
        public static List<BossGetAllData> create(List<Boss> bosses){
            return bosses.stream().map(BossGetAllData::new).collect(toList());
        }
    }
}
