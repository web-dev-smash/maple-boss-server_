package com.maple.common.boss.service;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import com.maple.common.boss.domain.BossRepository;
import com.maple.common.fixture.BossFixture;
import com.maple.common.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DomainBossServiceTest extends BaseServiceTest {

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private BossService bossService;

    @Test
    void 보스_생성_성공() {
        var boss = BossFixture.createBoss();

        boss = bossService.create(boss);

        val foundBoss = bossRepository.findById(boss.getId()).orElseThrow();

        assertThat(foundBoss).isEqualTo(boss);
    }

    @Test
    void 보스_상세_조회_성공() {
        final Boss boss = bossRepository.save(BossFixture.createBoss());

        val foundBoss = bossService.getBoss(boss.getId());

        assertThat(foundBoss).isEqualTo(boss);
    }

    @Test
    void 보스_상세_조회_실패_존재하지_않음() {
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> bossService.getBoss(1L));
    }

    @Test
    void 보스_전체_조회_성공() {
        boss1 = bossService.create(new Boss("A", 1, BossClass.EASY, 1, 2, 100L, 200L, 300L, 400L, 100, 5));
        boss2 = bossService.create(new Boss("A", 1, BossClass.HARD, 1, 2, 100L, 200L, 300L, 400L, 100, 5));
        boss3 = bossService.create(new Boss("A", 1, BossClass.NORMAL, 1, 2, 100L, 200L, 300L, 400L, 100, 5));

        val bosses = bossService.getBosses();

        assertThat(bosses).containsExactly(boss1, boss2, boss3);
    }
}
