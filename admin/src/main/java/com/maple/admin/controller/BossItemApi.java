package com.maple.admin.controller;

import com.maple.admin.controller.dto.FixedBossItemCreateDto.FixedBossItemCreateData;
import com.maple.admin.controller.dto.FixedBossItemCreateDto.FixedBossItemCreateRequest;
import com.maple.admin.controller.dto.FixedBossItemCreateDto.FixedBossItemCreateResponse;
import com.maple.admin.controller.dto.RandomBossItemCreateDto.RandomBossItemCreateData;
import com.maple.admin.controller.dto.RandomBossItemCreateDto.RandomBossItemCreateRequest;
import com.maple.admin.controller.dto.RandomBossItemCreateDto.RandomBossItemCreateResponse;
import com.maple.admin.service.BossItemAdminService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boss-item")
@RequiredArgsConstructor
public class BossItemApi {

    private final BossItemAdminService bossItemAdminService;

    /**
     * 고정 보스 아이템 생성
     */
    @PostMapping("/fixed")
    public FixedBossItemCreateResponse createFixedBossItem(@RequestBody FixedBossItemCreateRequest req) {
        val fixedBossItem = bossItemAdminService.createFixedBossItem(req.toDto());

        return new FixedBossItemCreateResponse(FixedBossItemCreateData.create(fixedBossItem));
    }

    /**
     * 랜덤 보스 아이템 생성
     */
    @PostMapping("/random")
    public RandomBossItemCreateResponse createFixedBossItem(@RequestBody RandomBossItemCreateRequest req) {
        val randomBossItem = bossItemAdminService.createRandomBossItem(req.toDto());

        return new RandomBossItemCreateResponse(RandomBossItemCreateData.create(randomBossItem));
    }
}
