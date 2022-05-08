package com.maple.api.service;

import com.maple.api.fixture.UserFixture;
import com.maple.api.service.dto.PartyCreateDto;
import com.maple.api.support.BaseServiceTest;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class DefaultPartyAppServiceTest extends BaseServiceTest {

    @Autowired
    private PartyAppService partyAppService;

    @Autowired
    private UserRepository userRepository;

    private User leader;

    @BeforeEach
    void setUp() {
        leader = userRepository.save(UserFixture.createUser());
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
}