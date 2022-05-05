package com.maple.admin.controller;

import com.maple.admin.controller.dto.BossCreateDto.BossCreateData;
import com.maple.admin.controller.dto.BossCreateDto.BossCreateRequest;
import com.maple.admin.controller.dto.BossCreateDto.BossCreateResponse;
import com.maple.admin.controller.dto.BossFindDto;
import com.maple.admin.controller.dto.BossFindDto.BossFindResponse;
import com.maple.common.boss.service.BossService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static com.maple.admin.controller.dto.BossFindDto.BossFindData.*;

@RestController
@RequestMapping("/boss")
@RequiredArgsConstructor
public class BossApi {

    private final BossService bossService;

    /**
     * 보스 생성
     */
    @PostMapping
    public BossCreateResponse createBoss(@RequestBody BossCreateRequest req) {
        val boss = bossService.create(req.toEntity());

        return new BossCreateResponse(BossCreateData.create(boss));
    }

    /**
     * 보스 상세 조회
     */
    @GetMapping("/{id}")
    public BossFindResponse findBoss(@PathVariable("id") Long id) {
        val foundBoss = bossService.findById(id);

        return new BossFindResponse(create(foundBoss));
    }
}
