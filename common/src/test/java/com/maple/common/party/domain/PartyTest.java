package com.maple.common.party.domain;

import com.maple.common.fixture.PartyFixture;
import com.maple.common.fixture.UserFixture;
import com.maple.common.user.domain.User;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static com.maple.common.exception.ErrorCode.*;
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
        leader = UserFixture.createUser();
        member = mock(User.class);
    }

    @Test
    void 파티_생성_성공() {
        val party = new Party(leader, "파티", "파티부가설명");

        assertThat(party.getName()).isEqualTo("파티");
        assertThat(party.getDescription()).isEqualTo("파티부가설명");
        assertThat(party.getLeader()).isEqualTo(leader);
        assertThat(party.getMembers()).isEmpty();
        assertThat(party.getStatus()).isEqualTo(PartyStatus.CREATED);
        assertThat(party.getCreateAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 파티_이름이_널이거나_빈값이면_실패(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Party(leader, name, "파티부가설명"));
    }

    @Test
    void 파티_이름이_공백이면_실패() {
        val emptyPartyName = "  ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Party(leader, emptyPartyName, "파티부가설명"));
    }

    @Test
    void 파티장이_널이면_실패() {
        assertThatNullPointerException().isThrownBy(() -> new Party(null, "파티", "파티부가설명"));
    }

    @Test
    void 파티원_추가() {
        val otherMember = mock(User.class);

        given(member.getId()).willReturn(1L);
        given(otherMember.getId()).willReturn(2L);

        val party = PartyFixture.createParty(leader);

        assertThat(party.getMembers()).isEmpty();

        party.addMember(member);
        party.addMember(otherMember);

        assertThat(party.getMembers()).containsExactly(member, otherMember);
    }

    @Test
    void 파티원이_널이면_파티원_추가_실패() {
        val party = PartyFixture.createParty(leader);

        assertThatNullPointerException().isThrownBy(() -> party.addMember(null));
    }

    @Test
    void 파티장을_추가혀려고하면_파티원_추가_실패() {
        val party = PartyFixture.createParty(leader);

        assertThatIllegalArgumentException().isThrownBy(() -> party.addMember(leader));
    }

    @Test
    void 최대_파티인원_보다_많이_추가하려고_하면_파티원_추가_실패() {
        val party = PartyFixture.createParty(leader);

        for (long id = 1L; id <= MAXIMUM_MEMBER; id++) {
            val member = mock(User.class);

            given(member.getId()).willReturn(id);

            party.addMember(member);
        }

        val notAllowedMember = mock(User.class);

        given(notAllowedMember.getId()).willReturn(MAXIMUM_MEMBER + 1L);

        assertThatMapleBossException(ALREADY_MAXIMUM_PARTY_MEMBER).isThrownBy(() -> party.addMember(notAllowedMember));
    }

    @Test
    void 이미_추가된_파티원이면_파티원_추가_실패() {
        given(member.getId()).willReturn(1L);

        val party = PartyFixture.createParty(leader);

        party.addMember(member);

        assertThatMapleBossException(ALREADY_EXISTS_PARTY_MEMBER).isThrownBy(() -> party.addMember(member));
    }

    @Test
    void 파티원_제거() {
        given(member.getId()).willReturn(1L);

        val party = PartyFixture.createParty(leader);

        party.addMember(member);

        assertThat(party.getMembers()).hasSize(1);

        party.removeMember(member);

        assertThat(party.getMembers()).hasSize(0);
    }

    @Test
    void 파티원이_널이면_파티원_제거_실패() {
        val party = PartyFixture.createParty(leader);

        assertThatNullPointerException().isThrownBy(() -> party.removeMember(null));
    }

    @Test
    void 없는_파티원을_제거하려고하면_파티원_제거_실패() {
        val party = PartyFixture.createParty(leader);

        party.addMember(member);

        val notContainsMember = mock(User.class);

        given(notContainsMember.getId()).willReturn(2L);

        assertThatMapleBossException(NOT_EXISTS_PARTY_MEMBER).isThrownBy(() -> party.removeMember(notContainsMember));
    }

    @Test
    void 파티_수정() {
        val party = new Party(leader, "name", "description");

        party.update("updated name", "updated description");

        assertThat(party.getName()).isEqualTo("updated name");
        assertThat(party.getDescription()).isEqualTo("updated description");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 파티_이름이_널이거나_빈값이면_파티_수정_실패(String name) {
        val party = PartyFixture.createParty(leader);

        assertThatIllegalArgumentException().isThrownBy(() -> party.update(name, "updated description"));
    }

    @Test
    void 파티_이름이_공백이면_파티_수정_실패() {
        val party = PartyFixture.createParty(leader);

        assertThatIllegalArgumentException().isThrownBy(() -> party.update(" ", "updated description"));
    }

    @Test
    void 파티장_변경() {
        val party = PartyFixture.createParty(leader);

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
    void 파티원이_널이면_파티장_변경_실패() {
        val party = PartyFixture.createParty(leader);

        assertThatNullPointerException().isThrownBy(() -> party.changeLeader(null));
    }
}
