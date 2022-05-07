package com.maple.common.party.domain;

import com.maple.common.support.BaseEntity;
import com.maple.common.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * 파티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Party extends BaseEntity {

    /* 이름 */
    @Column(length = 15, nullable = false)
    private String name;

    /* 부가설명 */
    @Column(length = 50)
    private String description;

    /* 파티장 */
    @ManyToOne(fetch = FetchType.LAZY)
    private User leader;

    /* 파티원 */
    @ManyToMany
    private List<User> members;

    /* 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PartyStatus status = PartyStatus.CREATED;

    /* 생성일 */
    private OffsetDateTime createAt = OffsetDateTime.now();

    public Party(String name, String description, User leader, List<User> members) {
        this.name = name;
        this.description = description;
        this.leader = leader;
        this.members = members;
    }
}
