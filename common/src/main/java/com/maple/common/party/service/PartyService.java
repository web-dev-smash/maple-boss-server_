package com.maple.common.party.service;

import com.maple.common.party.domain.Party;
import com.maple.common.user.domain.User;

import java.util.List;

public interface PartyService {

    /**
     * 파티 생성
     */
    Party create(Party party);

    /**
     * 파티 목록 조회
     */
    List<Party> getParties(User user);
}
