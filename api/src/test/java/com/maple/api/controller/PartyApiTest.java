package com.maple.api.controller;

import com.maple.api.controller.dto.PartyCreateDto.PartyCreateRequest;
import com.maple.api.fixture.UserFixture;
import com.maple.api.support.BaseApiTest;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PartyApiTest extends BaseApiTest {

    @Autowired
    private UserRepository userRepository;

    private User leader;

    @BeforeEach
    void setUp() {
        leader = userRepository.save(UserFixture.createUser());
    }

    @Test
    void 파티_생성() throws Exception {
        val req = new PartyCreateRequest(leader.getId(), "파티명", "파티 설명");

        mockMvc.perform(post("/party")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.party.id").isNotEmpty(),
                        jsonPath("$.party.leaderId").value(leader.getId()),
                        jsonPath("$.party.leaderNickname").value(leader.getNickname()),
                        jsonPath("$.party.name").value(req.name()),
                        jsonPath("$.party.description").value(req.description()),
                        jsonPath("$.party.createAt").isNotEmpty()
                );
    }
}