package com.maple.common.party.domain;

import com.google.common.base.Preconditions;
import com.maple.common.support.BaseEntity;
import com.maple.common.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * 파티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Party extends BaseEntity {

    /* 파티장 */
    @ManyToOne(fetch = FetchType.LAZY)
    private User leader;

    /* 파티원 */
    @ManyToMany
    private List<User> members;

    /* 이름 */
    @Column(length = 15, nullable = false)
    private String name;

    /* 부가설명 */
    @Column(length = 50)
    private String description;

    /* 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PartyStatus status = PartyStatus.CREATED;

    /* 생성일 */
    private OffsetDateTime createAt = OffsetDateTime.now();

    public Party(User leader, String name, String description) {
        checkNotNull(leader);

        checkArgument(Strings.isNotBlank(name));

        this.leader = leader;
        this.name = name;
        this.description = description;
    }

    public Party withMember(User member) {
//        if (members.contains(member)) {
//            throw new MapleBossException(ALREADY_EXISTS_MEMBER);
//        }

        this.members.add(member);

        return this;
    }
}
