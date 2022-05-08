package com.maple.admin.controller;

import com.maple.admin.controller.dto.FixedBossItemCreateDto.FixedBossItemCreateRequest;
import com.maple.admin.controller.dto.RandomBossItemCreateDto.RandomBossItemCreateRequest;
import com.maple.admin.support.BaseApiTest;
import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.service.BossService;
import com.maple.common.bossitem.domain.FixedBossItem;
import com.maple.common.bossitem.domain.FixedBossItemAmount;
import com.maple.common.bossitem.domain.RandomBossItem;
import com.maple.common.bossitem.domain.RandomBossItemAmount;
import com.maple.common.bossitem.service.BossItemService;
import com.maple.common.item.domain.Item;
import com.maple.common.item.service.ItemService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static com.maple.admin.fixture.BossFixture.createBoss;
import static com.maple.admin.fixture.ItemFixture.createItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BossItemApiTest extends BaseApiTest {

    @Autowired
    private BossService bossService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private BossItemService bossItemService;

    @Test
    void 고정_보스아이템_생성() throws Exception {
        val boss = bossService.create(createBoss());
        val item = itemService.create(createItem());

        val req = new FixedBossItemCreateRequest(boss.getId(), item.getId(), 1, 1, 10000L);

        mockMvc.perform(post("/boss-item/fixed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.fixedBossItem.id").isNotEmpty(),
                        jsonPath("$.fixedBossItem.minimumAmount").value(1),
                        jsonPath("$.fixedBossItem.maximumAmount").value(1),
                        jsonPath("$.fixedBossItem.price").value(10000L),
                        jsonPath("$.fixedBossItem.createAt").isNotEmpty()
                );
    }

    @Test
    void 랜덤_보스아이템_생성() throws Exception {
        val boss = bossService.create(createBoss());
        val item = itemService.create(createItem());

        val req = new RandomBossItemCreateRequest(boss.getId(), item.getId(), 1, 1);

        mockMvc.perform(post("/boss-item/random")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.randomBossItem.id").isNotEmpty(),
                        jsonPath("$.randomBossItem.minimumAmount").value(1),
                        jsonPath("$.randomBossItem.maximumAmount").value(1),
                        jsonPath("$.randomBossItem.createAt").isNotEmpty()
                );
    }

    @Test
    void 보스아이템_목록_조회() throws Exception {
        val boss = bossService.create(createBoss());
        val item1 = itemService.create(createItem());
        val item2 = itemService.create(createItem());

        bossItemService.createFixedBossItem(new FixedBossItem(boss, item1, new FixedBossItemAmount(1, 1), 1000L));
        bossItemService.createRandomBossItem(new RandomBossItem(boss, item2, new RandomBossItemAmount(1, 1)));

        mockMvc.perform(get("/boss-item/{bossId}", boss.getId()))
                .andExpect(status().isOk())
                .andExpectAll(보스아이템_목록_조회_검증(0, boss, item1))
                .andExpectAll(보스아이템_목록_조회_검증(1, boss, item2));
    }

    private ResultMatcher[] 보스아이템_목록_조회_검증(int index, Boss boss, Item item) {
        val indexString = String.valueOf(index);

        return List.of(
                jsonPath("""
                        $.bossItems[{index}].id""".replace("{index}", indexString)).isNotEmpty(),
                jsonPath("""
                        $.bossItems[{index}].bossName""".replace("{index}", indexString)).value(boss.getName()),
                jsonPath("""
                        $.bossItems[{index}].bossClass""".replace("{index}", indexString)).value(boss.getClazz().name()),
                jsonPath("""
                        $.bossItems[{index}].itemName""".replace("{index}", indexString)).value(item.getName()),
                jsonPath("""
                        $.bossItems[{index}].itemType""".replace("{index}", indexString)).value(item.getType().name()),
                jsonPath("""
                        $.bossItems[{index}].createAt""".replace("{index}", indexString)).isNotEmpty()
        ).toArray(ResultMatcher[]::new);
    }
}