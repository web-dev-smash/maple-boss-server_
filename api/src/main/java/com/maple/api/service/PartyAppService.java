package com.maple.api.service;

import com.maple.api.service.dto.PartyCreateDto;
import com.maple.common.party.domain.Party;

public interface PartyAppService {

    /**
     * 파티 생성
     */
    Party create(PartyCreateDto dto);
}
