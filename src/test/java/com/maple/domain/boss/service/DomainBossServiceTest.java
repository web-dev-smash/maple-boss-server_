package com.maple.domain.boss.service;

import com.maple.domain.boss.domain.BossRepository;
import com.maple.fixture.BossFixture;
import com.maple.support.BaseServiceTest;
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
}
