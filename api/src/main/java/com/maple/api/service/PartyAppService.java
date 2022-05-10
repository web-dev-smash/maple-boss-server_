package com.maple.api.service;

import com.maple.api.service.dto.PartyCreateDto;
import com.maple.common.party.domain.Party;
import com.maple.common.user.domain.User;
import org.springframework.data.util.Pair;

import java.util.List;

public interface PartyAppService {

    /**
     * 파티 생성
     */
    Party create(PartyCreateDto dto);

    /**
     * 나의 파티 목록 조회
     */
    Pair<User, List<Party>> getParties(long userId);
}
