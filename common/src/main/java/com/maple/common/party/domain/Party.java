package com.maple.common.party.domain;

import com.maple.common.support.BaseEntity;
import com.maple.common.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.maple.common.exception.ErrorCode.*;
import static com.maple.common.exception.MapleBossException.validate;
import static com.maple.common.party.domain.PartyStatus.CREATED;

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
    private OffsetDateTime createAt = OffsetDateTime.now();

    public static final int MAXIMUM_MEMBER = 3;

    public Party(User leader, String name, String description) {
        checkNotNull(leader);
        checkArgument(Strings.isNotBlank(name));

        this.leader = leader;
        this.name = name;
        this.description = description;
    }

    public long getLeaderId() {
        return this.leader.getId();
    }

    public String getLeaderNickname() {
        return this.leader.getNickname();
    }

    public void update(String name, String description) {
        checkArgument(name != null && !name.isEmpty() && !name.isBlank());

        this.name = name;
        this.description = description;
    }

    public void addMember(User member) {
        checkNotNull(member);
        checkArgument(!member.equals(leader));

        validate((members.size() < MAXIMUM_MEMBER), ALREADY_MAXIMUM_PARTY_MEMBER);
        validate(!members.contains(member), ALREADY_EXISTS_PARTY_MEMBER);

        this.members.add(member);
    }

    public void removeMember(User member) {
        checkNotNull(member);

        validate(members.contains(member), NOT_EXISTS_PARTY_MEMBER);

        this.members.remove(member);
    }

    public void changeLeader(User member) {
        checkNotNull(member);

        val previousLeader = leader;

        this.leader = member;

        removeMember(member);
        addMember(previousLeader);
    }
}
