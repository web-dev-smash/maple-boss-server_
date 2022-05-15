package com.maple.common.boss.service;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossRepository;
import com.maple.common.fixture.BossFixture;
import com.maple.common.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static com.maple.common.boss.domain.BossClass.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DomainBossServiceTest extends BaseServiceTest {

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private BossService bossService;

    @Test
    void 보스_생성() {
        var boss = new Boss("윌", 1, EASY, 1, 2, 100L, 200L, 300L, 400L, 100, 5);

        boss = bossService.create(boss);

        assertThat(boss.getId()).isNotNull();

        val foundBoss = bossRepository.findById(boss.getId()).orElseThrow();

        assertThat(foundBoss.getName()).isEqualTo("윌");
        assertThat(foundBoss.getLevel()).isEqualTo(1);
        assertThat(foundBoss.getClazz()).isEqualTo(EASY);
        assertThat(foundBoss.getEntryMinLevel()).isEqualTo(1);
        assertThat(foundBoss.getEntryMaxLevel()).isEqualTo(2);
        assertThat(foundBoss.getHpPhaseOne()).isEqualTo(100L);
        assertThat(foundBoss.getHpPhaseTwo()).isEqualTo(200L);
        assertThat(foundBoss.getHpPhaseThree()).isEqualTo(300L);
        assertThat(foundBoss.getHpPhaseFour()).isEqualTo(400L);
        assertThat(foundBoss.getArcaneForce()).isEqualTo(100);
        assertThat(foundBoss.getDeathLimit()).isEqualTo(5);
    }

    @Test
    void 보스_상세_조회() {
        final Boss boss = bossRepository.save(BossFixture.createBoss());

        val foundBoss = bossService.getBoss(boss.getId());

        assertThat(foundBoss).isEqualTo(boss);
    }

    @Test
    void 보스_전체_조회() {
        val boss1 = bossService.create(new Boss("A", 1, EASY, 1, 2, 100L, 200L, 300L, 400L, 100, 5));
        val boss2 = bossService.create(new Boss("A", 1, HARD, 1, 2, 100L, 200L, 300L, 400L, 100, 5));
        val boss3 = bossService.create(new Boss("A", 1, NORMAL, 1, 2, 100L, 200L, 300L, 400L, 100, 5));

        val bosses = bossService.getBosses();

        assertThat(bosses).containsExactly(boss1, boss2, boss3);
    }
}
