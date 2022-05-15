package com.maple.common.party.service;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyRepository;
import com.maple.common.support.BaseServiceTest;
import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.service.UserService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.maple.common.fixture.UserFixture.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class DomainPartyServiceTest extends BaseServiceTest {

    @Autowired
    private PartyService partyService;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CertCodeGenerator mockCertCodeGenerator;

    private User leader;

    @BeforeEach
    void setUp() {
        leader = userService.create(createUser(), mockCertCodeGenerator);
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
    void 파티_생성_실패__파티가_null() {
        assertThatNullPointerException().isThrownBy(() -> partyService.create(null));
    }
}