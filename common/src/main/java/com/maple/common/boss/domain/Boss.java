package com.maple.common.boss.domain;

import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.List;

import static com.maple.core.exception.Preconditions.require;
import static java.util.Collections.max;
import static java.util.Collections.min;

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

    public Boss(String name, int level, BossClass clazz, int entryMinLevel, int entryMaxLevel,
                Long hpPhaseOne, Long hpPhaseTwo, Long hpPhaseThree, Long hpPhaseFour,
                int arcaneForce, int deathLimit) {
        require(Strings.isNotBlank(name), "이름은 필수입니다.");
        require(max(List.of(entryMinLevel, entryMaxLevel)) <= MAX_LEVEL, "최대 입장 가능 레벨을 초과 할 수 없습니다.");
        require(min(List.of(entryMinLevel, entryMaxLevel)) >= MIN_LEVEL, "최소 입장 가능 레벨 미만일 수 없습니다.");
        require(entryMinLevel < entryMaxLevel, "입장 최소는 최대보다 작아야 합니다.");
        require(min(List.of(level, entryMinLevel, entryMaxLevel, arcaneForce, deathLimit)) > -1, "음수는 올 수 없습니다.");
        require(min(List.of(hpPhaseOne, hpPhaseTwo, hpPhaseThree, hpPhaseFour)) > -1, "음수는 올 수 없습니다.");

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
