package com.maple.admin.service;

import com.maple.common.party.domain.Party;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartyAdminService {


    /**
     * 전체 파티 목록 조회
     */
    Page<Party> getAllParties(Pageable pageable);
}
