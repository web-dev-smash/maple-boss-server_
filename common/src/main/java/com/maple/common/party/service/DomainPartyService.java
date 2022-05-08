package com.maple.common.party.service;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainPartyService implements PartyService {

    private final PartyRepository partyRepository;

    @Override
    public Party create(Party party) {
        checkNotNull(party);

        return partyRepository.save(party);
    }
}
