package com.maple.common.party.domain;

import com.maple.common.user.domain.User;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static com.maple.common.exception.ErrorCode.*;
import static com.maple.common.fixture.PartyFixture.createParty;
import static com.maple.common.party.domain.Party.MAXIMUM_MEMBER;
import static com.maple.common.support.MapleBossExceptionTest.assertThatMapleBossException;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PartyTest {

    private User leader;
    private User member;

    @BeforeEach
    void setUp() {
        leader = mock(User.class);
        member = mock(User.class);

        given(leader.getId()).willReturn(99L);
        given(leader.getNickname()).willReturn("닉네임");
    }

    @Test
    void 파티_생성_성공() {
        val party = new Party(leader, "파티", "파티부가설명");

        assertThat(party.getName()).isEqualTo("파티");
        assertThat(party.getDescription()).isEqualTo("파티부가설명");
        assertThat(party.getLeader()).isEqualTo(leader);
        assertThat(party.getLeaderId()).isEqualTo(leader.getId());
        assertThat(party.getLeaderNickname()).isEqualTo(leader.getNickname());
        assertThat(party.getMembers()).containsExactly(leader);
        assertThat(party.getStatus()).isEqualTo(PartyStatus.CREATED);
        assertThat(party.getCreateAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 파티_생성_실패__이름이_null_이거나_빈값(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Party(leader, name, "파티부가설명"));
    }

    @Test
    void 파티_생성_실패__이름이_공백() {
        val emptyPartyName = "  ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Party(leader, emptyPartyName, "파티부가설명"));
    }

    @Test
    void 파티_생성_실패__파티장이_null() {
        assertThatNullPointerException().isThrownBy(() -> new Party(null, "파티", "파티부가설명"));
    }

    @Test
    void 파티장이면_true() {
        val party = createParty(leader);

        assertThat(party.isLeader(leader)).isTrue();
    }

    @Test
    void 파티장아니면_false() {
        val party = createParty(leader);

        assertThat(party.isLeader(member)).isFalse();
    }

    @Test
    void 파티원_추가_성공() {
        val otherMember = mock(User.class);

        given(member.getId()).willReturn(1L);
        given(otherMember.getId()).willReturn(2L);

        val party = createParty(leader);

        assertThat(party.getMembers()).containsExactly(leader);

        party.addMember(member);
        party.addMember(otherMember);

        assertThat(party.getMembers()).containsExactly(leader, member, otherMember);
    }

    @Test
    void 파티원_추가_실패__파티장을_추가() {
        val party = createParty(leader);

        assertThatIllegalArgumentException().isThrownBy(() -> party.addMember(leader));
    }

    @Test
    void 파티원_추가_실패__파티원이_null() {
        val party = createParty(leader);

        assertThatNullPointerException().isThrownBy(() -> party.addMember(null));
    }

    @Test
    void 파티원_추가_실패__최대_파티_인원_보다_많이_추가() {
        val party = createParty(leader);

        for (long id = 1L; id < MAXIMUM_MEMBER; id++) {
            val member = mock(User.class);

            given(member.getId()).willReturn(id);

            party.addMember(member);
        }

        val notAllowedMember = mock(User.class);

        given(notAllowedMember.getId()).willReturn(MAXIMUM_MEMBER + 1L);

        assertThatMapleBossException(ALREADY_MAXIMUM_PARTY_MEMBER).isThrownBy(() -> party.addMember(notAllowedMember));
    }

    @Test
    void 파티원_추가_실패__이미_추가되어_있으면() {
        given(member.getId()).willReturn(1L);

        val party = createParty(leader);

        party.addMember(member);

        assertThatMapleBossException(ALREADY_EXISTS_PARTY_MEMBER).isThrownBy(() -> party.addMember(member));
    }

    @Test
    void 파티원_제거_성공() {
        given(member.getId()).willReturn(1L);

        val party = createParty(leader);

        party.addMember(member);

        assertThat(party.getMembers()).containsExactly(leader, member);

        party.removeMember(member);

        assertThat(party.getMembers()).containsExactly(leader);
    }

    @Test
    void 파티원_제거_실패__파티장을_제거() {
        val party = createParty(leader);

        assertThatIllegalArgumentException().isThrownBy(() -> party.removeMember(leader));
    }

    @Test
    void 파티원_제거_실패__파티원이_null() {
        val party = createParty(leader);

        assertThatNullPointerException().isThrownBy(() -> party.removeMember(null));
    }

    @Test
    void 파티원_제거_실패__존재하지_않으면() {
        val party = createParty(leader);

        party.addMember(member);

        val notContainsMember = mock(User.class);

        given(notContainsMember.getId()).willReturn(2L);

        assertThatMapleBossException(NOT_EXISTS_PARTY_MEMBER).isThrownBy(() -> party.removeMember(notContainsMember));
    }

    @Test
    void 파티_수정_성공() {
        val party = new Party(leader, "name", "description");

        party.update("updated name", "updated description");

        assertThat(party.getName()).isEqualTo("updated name");
        assertThat(party.getDescription()).isEqualTo("updated description");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 파티_수정_실패__이름이_null_이거나_빈값(String name) {
        val party = createParty(leader);

        assertThatIllegalArgumentException().isThrownBy(() -> party.update(name, "updated description"));
    }

    @Test
    void 파티_수정_실패__이름이_공백() {
        val party = createParty(leader);

        assertThatIllegalArgumentException().isThrownBy(() -> party.update(" ", "updated description"));
    }

    @Test
    void 파티장_변경_성공() {
        val party = createParty(leader);

        val member1 = mock(User.class);
        val member2 = mock(User.class);
        val member3 = mock(User.class);

        given(member1.getId()).willReturn(2L);
        given(member2.getId()).willReturn(3L);
        given(member3.getId()).willReturn(4L);

        party.addMember(member1);
        party.addMember(member2);
        party.addMember(member3);

        party.changeLeader(member1);

        assertThat(party.getLeader()).isEqualTo(member1);
        assertThat(party.getMembers()).contains(leader);
    }

    @Test
    void 파티장_변경_실패__파티원이_null() {
        val party = createParty(leader);

        assertThatNullPointerException().isThrownBy(() -> party.changeLeader(null));
    }

    @Test
    void 파티장_변경_실패__파티에_없는_사용자() {
        val party = createParty(leader);
        val fakeMember = mock(User.class);

        given(leader.getId()).willReturn(1L);
        given(member.getId()).willReturn(2L);
        given(fakeMember.getId()).willReturn(3L);

        party.addMember(member);

        assertThatIllegalArgumentException().isThrownBy(() -> party.changeLeader(fakeMember));
    }
}
