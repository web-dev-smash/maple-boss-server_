package com.maple.admin.service;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyRepository;
import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.service.UserService;
import com.maple.core.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static com.maple.admin.fixture.PartyFixture.createParty;
import static com.maple.admin.fixture.UserFixture.createUser;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultPartyAdminServiceTest extends BaseServiceTest {

    @Autowired
    private PartyAdminService partyAdminService;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CertCodeGenerator mockCertCodeGenerator;

    private User user1;
    private User user2;

    private Party party1;
    private Party party2;
    private Party party3;
    private Party party4;
    private Party party5;
    private Party party6;

    @BeforeEach
    void setUp() {
        user1 = userService.create(createUser(), mockCertCodeGenerator);
        user2 = userService.create(new User("user2", "1234", "user2", "user2"), mockCertCodeGenerator);

        party1 = partyRepository.save(createParty(user1));
        party2 = partyRepository.save(createParty(user1));
        party3 = partyRepository.save(createParty(user1));
        party4 = partyRepository.save(createParty(this.user2));
        party5 = partyRepository.save(createParty(this.user2));
        party6 = partyRepository.save(createParty(this.user2));
    }

    @Test
    void 파티_전체_목록_조회() {
        val page1Parties = partyAdminService.getAllParties(PageRequest.of(0, 4));
        val page2Parties = partyAdminService.getAllParties(PageRequest.of(1, 4));

        assertThat(page1Parties).containsExactly(party1, party2, party3, party4);
        assertThat(page2Parties).containsExactly(party5, party6);
    }
}