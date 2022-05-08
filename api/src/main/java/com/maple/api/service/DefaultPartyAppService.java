package com.maple.api.service;

import com.maple.api.service.dto.PartyCreateDto;
import com.maple.common.party.domain.Party;
import com.maple.common.party.service.PartyService;
import com.maple.common.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPartyAppService implements PartyAppService {

    private final PartyService partyService;

    private final UserRepository userRepository;

    @Override
    public Party create(PartyCreateDto dto) {
        checkNotNull(dto);

        val leader = userRepository.getById(dto.leaderId());

        val party = new Party(leader, dto.name(), dto.description());

        return partyService.create(party);
    }
}
