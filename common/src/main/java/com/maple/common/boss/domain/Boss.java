package com.maple.common.boss.domain;

import com.google.common.primitives.Longs;
import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 보스
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uq_class_name", columnNames = {"name", "class"})
)
public class Boss extends BaseEntity {

    /* 보스 이름 */
    private String name;

    /* 보스 레벨 */
    @Column(name = "level_")
    private int level;

    /* 보스 난이도 */
    @Enumerated(EnumType.STRING)
    @Column(name = "class")
    private BossClass clazz;

    /*  보스 입장 최소 레벨  */
    private int entryMinLevel;

    /*  보스 입장 최대 레벨  */
    private int entryMaxLevel;

    /*  보스 1페이지 체력  */
    private Long hpPhaseOne;

    /*  보스 2페이지 체력  */
    private Long hpPhaseTwo;

    /*  보스 3페이지 체력  */
    private Long hpPhaseThree;

    /*  보스 4페이지 체력  */
    private Long hpPhaseFour;

    /*  보스 아케인포스 요구량  */
    private int arcaneForce;

    /*  보스 도전 목숨 최대값 */
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
