package com.maple.admin.controller;

import com.maple.admin.controller.dto.BossCreateDto.BossCreateData;
import com.maple.admin.controller.dto.BossCreateDto.BossCreateRequest;
import com.maple.admin.controller.dto.BossCreateDto.BossCreateResponse;
import com.maple.admin.controller.dto.BossGetAllDto.BossGetAllData;
import com.maple.admin.controller.dto.BossGetAllDto.BossesGetAllResponse;
import com.maple.admin.controller.dto.BossGetDto.BossGetData;
import com.maple.admin.controller.dto.BossGetDto.BossGetResponse;
import com.maple.common.boss.service.BossService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

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
    public BossGetResponse getBoss(@PathVariable("id") Long id) {
        val boss = bossService.getBoss(id);

        return new BossGetResponse(BossGetData.create(boss));
    }

    /**
     * 보스 전체 조회
     */
    @GetMapping
    public BossesGetAllResponse getBosses() {
        val bosses = bossService.getBosses();

        return new BossesGetAllResponse(BossGetAllData.create(bosses));
    }
}
