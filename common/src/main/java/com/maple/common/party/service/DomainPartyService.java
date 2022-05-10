package com.maple.common.party.service;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyRepository;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainPartyService implements PartyService {

    private final PartyRepository partyRepository;
    private final UserRepository userRepository;

    @Override
    public Party create(Party party) {
        checkNotNull(party);

        return partyRepository.save(party);
    }

    @Override
    public List<Party> getParties(User user) {
        return partyRepository.findAllParty(user);
    }
}
