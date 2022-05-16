package com.maple.api.controller;

import com.maple.api.controller.dto.PartyCreateDto.PartyCreateRequest;
import com.maple.api.fixture.PartyFixture;
import com.maple.common.party.domain.Party;
import com.maple.common.party.service.PartyService;
import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.service.UserService;
import com.maple.core.support.BaseApiTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static com.maple.api.fixture.UserFixture.createUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PartyApiTest extends BaseApiTest {

    @Autowired
    private PartyService partyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CertCodeGenerator certCodeGenerator;

    private User user;
    private User member;

    @BeforeEach
    void setUp() {
        user = userService.create(createUser(), certCodeGenerator);
        member = userService.create(new User("member", "1234", "member", "member"), certCodeGenerator);
    }

    @Test
    void 파티_생성() throws Exception {
        val req = new PartyCreateRequest(user.getId(), "파티명", "파티 설명");

        mockMvc.perform(post("/party")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.party.id").isNotEmpty(),
                        jsonPath("$.party.leaderId").value(user.getId()),
                        jsonPath("$.party.leaderNickname").value(user.getNickname()),
                        jsonPath("$.party.name").value(req.name()),
                        jsonPath("$.party.description").value(req.description()),
                        jsonPath("$.party.createAt").isNotEmpty()
                );
    }

    @Test
    void 내_파티_목록_조회() throws Exception {
        val party1 = partyService.create(PartyFixture.createParty(user));
        val party2 = partyService.create(PartyFixture.createParty(user));
        val party3 = partyService.create(PartyFixture.createParty(user));

        val otherParty1 = partyService.create(PartyFixture.createParty(member));
        val otherParty2 = partyService.create(PartyFixture.createParty(member));
        val otherParty3 = partyService.create(PartyFixture.createParty(member));

        otherParty1.addMember(user);
        otherParty2.addMember(user);
        otherParty3.addMember(user);

        mockMvc.perform(get("/party")
                        .param("userId", String.valueOf(user.getId())))
                .andExpect(status().isOk())
                .andExpectAll(내_파티_목록_조회_검증(0, user, party1, user))
                .andExpectAll(내_파티_목록_조회_검증(1, user, party2, user))
                .andExpectAll(내_파티_목록_조회_검증(2, user, party3, user))
                .andExpectAll(내_파티_목록_조회_검증(3, member, otherParty1, user))
                .andExpectAll(내_파티_목록_조회_검증(4, member, otherParty2, user))
                .andExpectAll(내_파티_목록_조회_검증(5, member, otherParty3, user));
    }

    private ResultMatcher[] 내_파티_목록_조회_검증(int index, User leader, Party party, User member) {
        val indexString = String.valueOf(index);

        return List.of(
                jsonPath("$.parties[{index}].id".replace("{index}", indexString)).isNotEmpty(),
                jsonPath("$.parties[{index}].leaderId".replace("{index}", indexString)).value(leader.getId()),
                jsonPath("$.parties[{index}].leaderNickname".replace("{index}", indexString)).value(leader.getNickname()),
                jsonPath("$.parties[{index}].name".replace("{index}", indexString)).value(party.getName()),
                jsonPath("$.parties[{index}].description".replace("{index}", indexString)).value(party.getDescription()),
                jsonPath("$.parties[{index}].isLeader".replace("{index}", indexString)).value(party.isLeader(member)),
                jsonPath("$.parties[{index}].createAt".replace("{index}", indexString)).isNotEmpty()
        ).toArray(ResultMatcher[]::new);
    }
}