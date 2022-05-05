package com.maple.admin.controller;

import com.maple.admin.support.BaseApiTest;
import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import com.maple.common.boss.domain.BossRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.maple.admin.controller.dto.BossCreateDto.BossCreateRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BossApiTest extends BaseApiTest {

    @Autowired
    BossRepository bossRepository;

    @Test
    void 보스_생성() throws Exception {
        val req = new BossCreateRequest(
                "윌", 100, BossClass.EASY, 30, 50,
                100L, 200L, 300L, 400L,
                100, 3
        );

        mockMvc.perform(post("/boss")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.boss.id").isNotEmpty(),
                        jsonPath("$.boss.name").value("윌"),
                        jsonPath("$.boss.level").value(100),
                        jsonPath("$.boss.clazz").value(BossClass.EASY.name()),
                        jsonPath("$.boss.entryMinLevel").value(30),
                        jsonPath("$.boss.entryMaxLevel").value(50),
                        jsonPath("$.boss.hpPhaseOne").value(100L),
                        jsonPath("$.boss.hpPhaseTwo").value(200L),
                        jsonPath("$.boss.hpPhaseThree").value(300L),
                        jsonPath("$.boss.hpPhaseFour").value(400L),
                        jsonPath("$.boss.totalHpPhase").value(100L + 200L + 300L + 400L),
                        jsonPath("$.boss.arcaneForce").value(100),
                        jsonPath("$.boss.deathLimit").value(3)
                );
    }

    @Test
    void 보스_상세_조회() throws Exception {
        val createBoss = new Boss(
                "윌", 100, BossClass.EASY, 30, 50,
                100L, 200L, 300L, 400L,
                100, 3
        );
        val save = bossRepository.save(createBoss);

        mockMvc.perform(get("/boss/" + save.getId()))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.boss.name").value("윌"),
                        jsonPath("$.boss.level").value(100),
                        jsonPath("$.boss.clazz").value(BossClass.EASY.name()),
                        jsonPath("$.boss.entryMinLevel").value(30),
                        jsonPath("$.boss.entryMaxLevel").value(50),
                        jsonPath("$.boss.hpPhaseOne").value(100L),
                        jsonPath("$.boss.hpPhaseTwo").value(200L),
                        jsonPath("$.boss.hpPhaseThree").value(300L),
                        jsonPath("$.boss.hpPhaseFour").value(400L),
                        jsonPath("$.boss.totalHpPhase").value(100L + 200L + 300L + 400L),
                        jsonPath("$.boss.arcaneForce").value(100),
                        jsonPath("$.boss.deathLimit").value(3)
                );
    }
}