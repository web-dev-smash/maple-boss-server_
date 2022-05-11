package com.maple.admin.service;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPartyAdminService implements PartyAdminService {

    private final PartyRepository partyRepository;

    @Override
    public Page<Party> getAllParties(Pageable pageable) {
        return partyRepository.findAll(pageable);
    }
}
