package com.maple.common.boss.domain;

import com.google.common.primitives.Longs;
import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Boss extends BaseEntity {
    private String name;

    @Column(name = "level_")
    private int level;

    @Enumerated(EnumType.STRING)
    @Column(name = "class")
    private BossClass clazz;

    private int entryMinLevel;

    private int entryMaxLevel;

    private Long hpPhaseOne;

    private Long hpPhaseTwo;

    private Long hpPhaseThree;

    private Long hpPhaseFour;

    private int arcaneForce;

    private int deathLimit;

    // TODO: 2022/04/09 유저 도메인 만들면 옮겨야함
    public static final int MAX_LEVEL = 300;

    // TODO: 2022/04/09 정확한 최소 레벨 확인 후 변경 필요
    public static final int MIN_LEVEL = 0;

    public Boss(final String name, final int level, final BossClass clazz, final int entryMinLevel, final int entryMaxLevel,
                final Long hpPhaseOne, final Long hpPhaseTwo, final Long hpPhaseThree, final Long hpPhaseFour,
                final int arcaneForce, final int deathLimit) {
        checkArgument(Strings.isNotBlank(name), "이름은 필수입니다.");
        checkArgument(Longs.max(entryMinLevel, entryMaxLevel) <= MAX_LEVEL, "최대 입장 가능 레벨을 초과 할 수 없습니다.");
        checkArgument(Longs.min(entryMinLevel, entryMaxLevel) >= MIN_LEVEL, "최소 입장 가능 레벨 미만일 수 없습니다.");
        checkArgument(entryMinLevel < entryMaxLevel, "입장 최소는 최대보다 작아야 합니다.");
        checkArgument(Longs.min(level, entryMinLevel, entryMaxLevel, hpPhaseOne, hpPhaseTwo, hpPhaseThree, hpPhaseFour, arcaneForce, deathLimit) > -1, "음수는 올 수 없습니다.");

        this.name = name;
        this.level = level;
        this.clazz = clazz;
        this.entryMinLevel = entryMinLevel;
        this.entryMaxLevel = entryMaxLevel;
        this.hpPhaseOne = hpPhaseOne;
        this.hpPhaseTwo = hpPhaseTwo;
        this.hpPhaseThree = hpPhaseThree;
        this.hpPhaseFour = hpPhaseFour;
        this.arcaneForce = arcaneForce;
        this.deathLimit = deathLimit;
    }

    public long totalHpPhase() {
        return hpPhaseOne + hpPhaseTwo + hpPhaseThree + hpPhaseFour;
    }
}
