package com.maple.common.party.domain;

import com.maple.common.fixture.PartyFixture;
import com.maple.common.fixture.UserFixture;
import com.maple.common.support.BaseRepositoryTest;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class PartyRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    private User leader;

    @BeforeEach
    void setUp() {
        leader = userRepository.save(UserFixture.createUser());
    }

    @Test
    void 파티_생성_성공() {
        val party = partyRepository.save(PartyFixture.createParty(leader));

        assertThat(party.getId()).isNotNull();

        val foundParty = partyRepository.findById(party.getId()).orElseThrow();

        assertThat(foundParty).isEqualTo(party);
    }
}