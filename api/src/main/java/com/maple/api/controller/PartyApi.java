package com.maple.api.controller;

import com.maple.api.controller.dto.PartyCreateDto.PartyCreateData;
import com.maple.api.controller.dto.PartyCreateDto.PartyCreateRequest;
import com.maple.api.controller.dto.PartyCreateDto.PartyCreateResponse;
import com.maple.api.controller.dto.PartyGetAllDto.PartyGetAllData;
import com.maple.api.controller.dto.PartyGetAllDto.PartyGetAllResponse;
import com.maple.api.service.PartyAppService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/party")
@RequiredArgsConstructor
public class PartyApi {

    private final PartyAppService partyAppService;

    /**
     * 파티 생성
     * <p>
     * TODO: Security 이후 로그인한 유저 ID가 leader ID로 대체
     */
    @PostMapping
    public PartyCreateResponse createParty(@RequestBody PartyCreateRequest req) {
        val party = partyAppService.create(req.toDto());

        return new PartyCreateResponse(PartyCreateData.create(party));
    }

    /**
     * 나의 파티 목록 조회
     * TODO: Security 이후 로그인한 유저 ID가 user ID로 대체
     */
    @GetMapping
    public PartyGetAllResponse getParties(long userId) {
        val parties = partyAppService.getParties(userId);

        return new PartyGetAllResponse(PartyGetAllData.create(parties));
    }
}
