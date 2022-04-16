package com.maple.common.boss.domain;

import com.maple.common.fixture.BossFixture;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BossTest {
    @Test
    void 보스생성_성공() {
        val boss = new Boss("윌", 1, BossClass.EASY, 1, 2, 100L, 200L, 300L, 0L, 100, 5);

        assertThat(boss.getName()).isEqualTo("윌");
        assertThat(boss.getLevel()).isEqualTo(1);
        assertThat(boss.getClazz()).isEqualTo(BossClass.EASY);
        assertThat(boss.getEntryMinLevel()).isEqualTo(1);
        assertThat(boss.getEntryMaxLevel()).isEqualTo(2);
        assertThat(boss.getHpPhaseOne()).isEqualTo(100L);
        assertThat(boss.getHpPhaseTwo()).isEqualTo(200L);
        assertThat(boss.getHpPhaseThree()).isEqualTo(300L);
        assertThat(boss.getHpPhaseFour()).isEqualTo(0L);
        assertThat(boss.getArcaneForce()).isEqualTo(100);
        assertThat(boss.getDeathLimit()).isEqualTo(5);
    }

    @Test
    void 보스_입장_레벨_최소가_최대보다_크면_실패() {
        val wrongMinLevel = 2;
        val wrongMaxLevel = 1;

        assertThatIllegalArgumentException().isThrownBy(() -> new Boss("윌", 1, BossClass.EASY, wrongMinLevel, wrongMaxLevel, 100L, 200L, 300L, 0L, 100, 5));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 보스_이름이_널이거나_빈값이면_실패(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Boss(name, 1, BossClass.EASY, 1, 2, 100L, 200L, 300L, 0L, 100, 5));
    }

    @Test
    void 보스_이름이_공백이면_실패() {
        val emptyBossName = "  ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Boss(emptyBossName, 1, BossClass.EASY, 1, 2, 100L, 200L, 300L, 0L, 100, 5));
    }

    @Test
    void 보스_입장레벨이_사용자_최대레벨_보다_크면_실패() {
        val wrongMaxLevel = Boss.MAX_LEVEL + 1;

        assertThatIllegalArgumentException().isThrownBy(() -> new Boss("윌", 1, BossClass.EASY, 1, wrongMaxLevel, 100L, 200L, 300L, 0L, 100, 5));
    }

    @Test
    void 보스_입장레벨이_사용자_최소레벨_보다_작으면_실패() {
        val wrongMinLevel = Boss.MIN_LEVEL - 1;

        assertThatIllegalArgumentException().isThrownBy(() -> new Boss("윌", 1, BossClass.EASY, wrongMinLevel, 2, 100L, 200L, 300L, 0L, 100, 5));
    }

    @Test
    void 음수가_오면_실패() {
        val negativeNumber = -1;

        assertThatIllegalArgumentException().isThrownBy(() -> new Boss("  ", 1, BossClass.EASY, negativeNumber, 2, 100L, 200L, 300L, 0L, 100, 5));
    }

    @Test
    void HP_페이즈의_총합을_구할수_있다() {
        val boss = BossFixture.createBoss();

        assertThat(boss.totalHpPhase()).isEqualTo(100L + 200L + 300L + 400L);
    }
}