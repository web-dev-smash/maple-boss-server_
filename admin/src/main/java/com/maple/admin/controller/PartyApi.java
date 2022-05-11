package com.maple.admin.controller;

import com.maple.admin.controller.dto.PartyGetDto.PartiesGetResponse;
import com.maple.admin.service.PartyAdminService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/party")
public class PartyApi {

    private final PartyAdminService partyAdminService;

    @GetMapping
    public PartiesGetResponse getAllParties(Pageable pageable) {
        val parties = partyAdminService.getAllParties(pageable);

        return PartiesGetResponse.create(parties);
    }
}
