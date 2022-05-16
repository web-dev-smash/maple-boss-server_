package com.maple.admin.controller;

import com.maple.admin.fixture.BossFixture;
import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossClass;
import com.maple.common.boss.domain.BossRepository;
import com.maple.core.support.BaseApiTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

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
        val boss = bossRepository.save(BossFixture.createBoss());

        mockMvc.perform(get("/boss/{id}", boss.getId()))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.boss.name").value(boss.getName()),
                        jsonPath("$.boss.level").value(boss.getLevel()),
                        jsonPath("$.boss.clazz").value(BossClass.EASY.name()),
                        jsonPath("$.boss.entryMinLevel").value(boss.getEntryMinLevel()),
                        jsonPath("$.boss.entryMaxLevel").value(boss.getEntryMaxLevel()),
                        jsonPath("$.boss.hpPhaseOne").value(boss.getHpPhaseOne()),
                        jsonPath("$.boss.hpPhaseTwo").value(boss.getHpPhaseTwo()),
                        jsonPath("$.boss.hpPhaseThree").value(boss.getHpPhaseThree()),
                        jsonPath("$.boss.hpPhaseFour").value(boss.getHpPhaseFour()),
                        jsonPath("$.boss.totalHpPhase").value(boss.totalHpPhase()),
                        jsonPath("$.boss.arcaneForce").value(boss.getArcaneForce()),
                        jsonPath("$.boss.deathLimit").value(boss.getDeathLimit())
                );
    }

    @Test
    void 보스_목록_조회() throws Exception {
        val boss1 = bossRepository.save(new Boss("A", 1, BossClass.EASY, 1, 2, 100L, 200L, 300L, 400L, 100, 5));
        val boss2 = bossRepository.save(new Boss("A", 1, BossClass.NORMAL, 1, 2, 100L, 200L, 300L, 400L, 100, 5));
        val boss3 = bossRepository.save(new Boss("A", 1, BossClass.HARD, 1, 2, 100L, 200L, 300L, 400L, 100, 5));

        mockMvc.perform(get("/boss"))
                .andExpectAll(status().isOk())
                .andExpectAll(보스_목록_조회_검증(0, boss1))
                .andExpectAll(보스_목록_조회_검증(1, boss2))
                .andExpectAll(보스_목록_조회_검증(2, boss3));
    }

    private ResultMatcher[] 보스_목록_조회_검증(int index, Boss boss) {
        val indexString = String.valueOf(index);

        return List.of(
                jsonPath("$.boss[{index}].id".replace("{index}", indexString)).isNotEmpty(),
                jsonPath("$.boss[{index}].name".replace("{index}", indexString)).value(boss.getName()),
                jsonPath("$.boss[{index}].level".replace("{index}", indexString)).value(boss.getLevel()),
                jsonPath("$.boss[{index}].clazz".replace("{index}", indexString)).value(boss.getClazz().name()),
                jsonPath("$.boss[{index}].entryMinLevel".replace("{index}", indexString)).value(boss.getEntryMinLevel()),
                jsonPath("$.boss[{index}].entryMaxLevel".replace("{index}", indexString)).value(boss.getEntryMaxLevel()),
                jsonPath("$.boss[{index}].hpPhaseOne".replace("{index}", indexString)).value(boss.getHpPhaseOne()),
                jsonPath("$.boss[{index}].hpPhaseTwo".replace("{index}", indexString)).value(boss.getHpPhaseTwo()),
                jsonPath("$.boss[{index}].hpPhaseThree".replace("{index}", indexString)).value(boss.getHpPhaseThree()),
                jsonPath("$.boss[{index}].hpPhaseFour".replace("{index}", indexString)).value(boss.getHpPhaseFour()),
                jsonPath("$.boss[{index}].totalHpPhase".replace("{index}", indexString)).value(boss.totalHpPhase()),
                jsonPath("$.boss[{index}].arcaneForce".replace("{index}", indexString)).value(boss.getArcaneForce()),
                jsonPath("$.boss[{index}].deathLimit".replace("{index}", indexString)).value(boss.getDeathLimit())
        ).toArray(ResultMatcher[]::new);
    }
}