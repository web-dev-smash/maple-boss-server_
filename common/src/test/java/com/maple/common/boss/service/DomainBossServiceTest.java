package com.maple.common.boss.service;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossRepository;
import com.maple.common.fixture.BossFixture;
import com.maple.common.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

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
    void 보스_상세_조회_테스트() {
        final Boss save = bossRepository.save(BossFixture.createBoss());

        val foundBoss = bossService.findById(save.getId());

        assertThat(foundBoss).isEqualTo(save);
    }
}
