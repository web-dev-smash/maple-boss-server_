package com.maple.common.party.domain;

import com.maple.common.support.BaseRepositoryTest;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.maple.common.fixture.PartyFixture.createParty;
import static com.maple.common.fixture.UserFixture.createUser;
import static org.assertj.core.api.Assertions.assertThat;

class PartyRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    private User leader;
    private User member;

    @BeforeEach
    void setUp() {
        leader = userRepository.save(createUser());

        member = userRepository.save(new User("member1", "1234", "member1", "member1", "member1@gmail.com"));
    }

    @Test
    void 파티_생성_성공() {
        val party = partyRepository.save(createParty(leader));

        assertThat(party.getId()).isNotNull();

        val foundParty = partyRepository.findById(party.getId()).orElseThrow();

        assertThat(foundParty).isEqualTo(party);
    }

    @Test
    void 나의_파티목록_조회() {
        val party1 = partyRepository.save(createParty(leader));
        val party2 = partyRepository.save(createParty(leader));
        val party3 = partyRepository.save(createParty(leader));
        val otherParty1 = partyRepository.save(createParty(leader));
        val otherParty2 = partyRepository.save(createParty(leader));

        party1.addMember(member);
        party2.addMember(member);
        party3.addMember(member);

        val parties = partyRepository.findAllParty(member);

        assertThat(parties).containsExactly(party1, party2, party3);
        assertThat(parties).doesNotContain(otherParty1, otherParty2);
    }
}