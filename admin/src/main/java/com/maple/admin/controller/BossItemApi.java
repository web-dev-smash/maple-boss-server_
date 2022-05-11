package com.maple.admin.controller;

import com.maple.admin.controller.dto.BossItemGetDto.BossItemsGetData;
import com.maple.admin.controller.dto.BossItemGetDto.BossItemsGetResponse;
import com.maple.admin.controller.dto.FixedBossItemCreateDto.FixedBossItemCreateData;
import com.maple.admin.controller.dto.FixedBossItemCreateDto.FixedBossItemCreateRequest;
import com.maple.admin.controller.dto.FixedBossItemCreateDto.FixedBossItemCreateResponse;
import com.maple.admin.controller.dto.RandomBossItemCreateDto.RandomBossItemCreateData;
import com.maple.admin.controller.dto.RandomBossItemCreateDto.RandomBossItemCreateRequest;
import com.maple.admin.controller.dto.RandomBossItemCreateDto.RandomBossItemCreateResponse;
import com.maple.admin.service.BossItemAdminService;
import com.maple.common.bossitem.service.DomainBossItemService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boss-item")
@RequiredArgsConstructor
public class BossItemApi {

    private final BossItemAdminService bossItemAdminService;

    private final DomainBossItemService domainBossItemService;

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

    /**
     * 보스 아이템 목록 조회
     */
    @GetMapping("/{bossId}")
    public BossItemsGetResponse getBossItems(@PathVariable long bossId) {
        val bossItems = domainBossItemService.getBossItems(bossId);

        return new BossItemsGetResponse(BossItemsGetData.create(bossItems));
    }
}
