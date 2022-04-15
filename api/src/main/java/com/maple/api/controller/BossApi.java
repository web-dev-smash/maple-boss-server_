package com.maple.api.controller;

import com.maple.api.controller.dto.BossCreateDto.BossCreateData;
import com.maple.api.controller.dto.BossCreateDto.BossCreateRequest;
import com.maple.api.controller.dto.BossCreateDto.BossCreateResponse;
import com.maple.common.boss.service.BossService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boss")
@RequiredArgsConstructor
public class BossApi {

    private final BossService bossService;

    @PostMapping
    public BossCreateResponse createBoss(@RequestBody BossCreateRequest req) {
        val boss = bossService.create(req.toEntity());

        return new BossCreateResponse(BossCreateData.create(boss));
    }
}
