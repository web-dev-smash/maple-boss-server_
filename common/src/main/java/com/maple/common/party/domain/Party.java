package com.maple.common.party.domain;

import com.maple.common.support.BaseEntity;
import com.maple.common.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.maple.common.party.domain.PartyStatus.CREATED;
import static com.maple.core.exception.ErrorCode.*;
import static com.maple.core.exception.Preconditions.*;

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
    private final List<User> members = new ArrayList<>();

    /* 이름 */
    @Column(length = 15, nullable = false)
    private String name;

    /* 부가설명 */
    @Column(length = 50)
    private String description;

    /* 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PartyStatus status = CREATED;

    /* 생성일 */
    private final OffsetDateTime createAt = OffsetDateTime.now();

    public static final int MAXIMUM_MEMBER = 6;

    public Party(User leader, String name, String description) {
        notNull(leader);

        require(Strings.isNotBlank(name));

        this.leader = leader;
        this.name = name;
        this.description = description;
        this.members.add(leader);
    }

    public long getLeaderId() {
        return this.leader.getId();
    }

    public String getLeaderNickname() {
        return this.leader.getNickname();
    }

    public boolean isLeader(User user) {
        return leader.equals(user);
    }

    public void update(String name, String description) {
        require(Strings.isNotBlank(name));

        this.name = name;
        this.description = description;
    }

    public void addMember(User member) {
        notNull(member);

        require(!isLeader(member));

        validate((members.size() < MAXIMUM_MEMBER), ALREADY_MAXIMUM_PARTY_MEMBER);
        validate(!members.contains(member), ALREADY_EXISTS_PARTY_MEMBER);

        this.members.add(member);
    }

    public void removeMember(User member) {
        notNull(member);

        require(!isLeader(member));

        validate(members.contains(member), NOT_EXISTS_PARTY_MEMBER);

        this.members.remove(member);
    }

    public void changeLeader(User member) {
        notNull(member);

        require(members.contains(member));

        this.leader = member;
    }
}
