package com.maple.common.party.service;

import com.maple.common.fixture.UserFixture;
import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyRepository;
import com.maple.common.support.BaseServiceTest;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class DomainPartyServiceTest extends BaseServiceTest {

    @Autowired
    private PartyService partyService;

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
        var party = new Party(leader, "파티명", "파티설명");

        party = partyService.create(party);

        assertThat(party.getId()).isNotNull();

        val foundParty = partyRepository.findById(party.getId()).orElseThrow();

        assertThat(foundParty.getLeader()).isEqualTo(leader);
        assertThat(foundParty.getName()).isEqualTo("파티명");
        assertThat(foundParty.getDescription()).isEqualTo("파티설명");
    }

    @Test
    void 파티가_null_이면_생성_실패() {
        assertThatNullPointerException().isThrownBy(() -> partyService.create(null));
    }
}