package com.maple.common.party.service;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyRepository;
import com.maple.common.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.maple.core.exception.Preconditions.notNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainPartyService implements PartyService {

    private final PartyRepository partyRepository;

    @Override
    public Party create(Party party) {
        notNull(party);

        return partyRepository.save(party);
    }

    @Override
    public List<Party> getParties(User user) {
        notNull(user);

        return partyRepository.findAllParty(user);
    }
}
