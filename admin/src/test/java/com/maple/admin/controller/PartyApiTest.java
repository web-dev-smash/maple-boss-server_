package com.maple.admin.controller;

import com.maple.admin.service.PartyAdminService;
import com.maple.admin.support.BaseApiTest;
import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyRepository;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static com.maple.admin.fixture.PartyFixture.createParty;
import static com.maple.admin.fixture.UserFixture.createUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PartyApiTest extends BaseApiTest {

    @Autowired
    private PartyAdminService partyAdminService;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

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
        user1 = userRepository.save(createUser());
        user2 = userRepository.save(new User("user2", "1234", "user2", "user2", "user2@gmail.com"));

        party1 = partyRepository.save(createParty(user1));
        party2 = partyRepository.save(createParty(user1));
        party3 = partyRepository.save(createParty(user1));
        party4 = partyRepository.save(createParty(user2));
        party5 = partyRepository.save(createParty(user2));
        party6 = partyRepository.save(createParty(user2));
    }

    @Test
    void 파티_전체_목록_조회() throws Exception {
        mockMvc.perform(get("/party")
                        .param("page", "0")
                        .param("size", "3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(파티_목록_조회_검증(0, party1));
    }

    private ResultMatcher[] 파티_목록_조회_검증(int index, Party party) {
        val indexString = String.valueOf(index);

        return List.of(
                jsonPath("$.parties[{index}].id".replace("index", indexString)).value(party.getId())
        ).toArray(ResultMatcher[]::new);
    }


}