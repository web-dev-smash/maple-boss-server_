package com.maple.domain.boss.domain;

import com.maple.fixture.BossFixture;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class BossRepositoryTest {

    @Autowired
    private BossRepository bossRepository;

    @Test
    void 보스_생성_성공() {
        val boss = bossRepository.save(BossFixture.createBoss());

        val foundBoss = bossRepository.findById(boss.getId()).orElseThrow();

        assertThat(foundBoss.getId()).isNotNull();
    }
}
