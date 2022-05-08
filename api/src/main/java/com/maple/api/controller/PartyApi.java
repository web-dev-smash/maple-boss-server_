package com.maple.api.controller;

import com.maple.api.controller.dto.PartyCreateDto.PartyCreateData;
import com.maple.api.controller.dto.PartyCreateDto.PartyCreateRequest;
import com.maple.api.controller.dto.PartyCreateDto.PartyCreateResponse;
import com.maple.api.service.PartyAppService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/party")
@RequiredArgsConstructor
public class PartyApi {

    private final PartyAppService partyAppService;

    /**
     * 파티 생성
     */
    @PostMapping
    public PartyCreateResponse createParty(@RequestBody PartyCreateRequest req) {
        val party = partyAppService.create(req.toDto());

        return new PartyCreateResponse(PartyCreateData.create(party));
    }
}
