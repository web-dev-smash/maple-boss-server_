package com.maple.common.boss.domain;

import com.maple.common.fixture.BossFixture;
import com.maple.common.support.BaseRepositoryTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class BossRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private BossRepository bossRepository;

    @Test
    void 보스_생성_성공() {
        val boss = bossRepository.save(BossFixture.createBoss());

        assertThat(boss.getId()).isNotNull();

        val foundBoss = bossRepository.findById(boss.getId()).orElseThrow();

        assertThat(foundBoss).isEqualTo(boss);
    }
}
