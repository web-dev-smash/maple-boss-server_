package com.maple.common.party.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import com.maple.common.fixture.UserFixture;
import com.maple.common.user.domain.User;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PartyTest {

    private User leader;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        leader = UserFixture.createUser();

        user1 = new User("user1", "1234", "name1", "nickname1", "user1@naver.com");
        user2 = new User("user2", "1234", "name2", "nickname2", "user2@naver.com");
        user3 = new User("user3", "1234", "name3", "nickname3", "user3@naver.com");
    }

    @Test
    void 파티_생성_성공() {
        val party = new Party(leader, "파티", "파티부가설명");

        assertThat(party.getName()).isEqualTo("파티");
        assertThat(party.getDescription()).isEqualTo("파티부가설명");
        assertThat(party.getLeader()).isEqualTo(leader);
        assertThat(party.getMembers()).isNull();
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
    void 파티원_추가_성공() {

    }
}
