package com.maple.api.service;

import com.maple.api.service.dto.PartyCreateDto;
import com.maple.api.support.BaseServiceTest;
import com.maple.common.party.service.PartyService;
import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.service.UserService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.maple.api.fixture.PartyFixture.createParty;
import static com.maple.api.fixture.UserFixture.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class DefaultPartyAppServiceTest extends BaseServiceTest {

    @Autowired
    private PartyAppService partyAppService;

    @Autowired
    private PartyService partyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CertCodeGenerator certCodeGenerator;

    private User leader;
    private User otherLeader;

    @BeforeEach
    void setUp() {
        leader = userService.create(createUser(), certCodeGenerator);
        otherLeader = userService.create(new User("member", "1234", "member", "member"), certCodeGenerator);
    }

    @Test
    void 파티_생성_성공() {
        val dto = new PartyCreateDto(leader.getId(), "파티명", "파티설명");

        val party = partyAppService.create(dto);

        assertThat(party).isNotNull();
    }

    @Test
    void dto가_null_이면_파티_생성_실패() {
        assertThatNullPointerException().isThrownBy(() -> partyAppService.create(null));
    }

    @Test
    void 내_파티_목록_조회() {
        val party1 = partyService.create(createParty(leader));
        val party2 = partyService.create(createParty(leader));
        val party3 = partyService.create(createParty(leader));

        val otherParty1 = partyService.create(createParty(otherLeader));
        val otherParty2 = partyService.create(createParty(otherLeader));
        val otherParty3 = partyService.create(createParty(otherLeader));

        val noneParty1 = partyService.create(createParty(otherLeader));
        val noneParty2 = partyService.create(createParty(otherLeader));
        val noneParty3 = partyService.create(createParty(otherLeader));

        otherParty1.addMember(leader);
        otherParty2.addMember(leader);
        otherParty3.addMember(leader);

        val pair = partyAppService.getParties(leader.getId());

        assertThat(pair.getFirst()).isEqualTo(leader);
        assertThat(pair.getSecond())
                .containsExactly(party1, party2, party3, otherParty1, otherParty2, otherParty3);
        assertThat(pair.getSecond())
                .doesNotContain(noneParty1, noneParty2, noneParty3);
    }
}