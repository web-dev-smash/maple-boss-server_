package com.maple.api.service;

import com.maple.api.service.dto.PartyCreateDto;
import com.maple.common.party.domain.Party;
import com.maple.common.party.service.PartyService;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.maple.core.exception.Preconditions.notNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPartyAppService implements PartyAppService {

    private final PartyService partyService;

    private final UserRepository userRepository;

    @Override
    public Party create(PartyCreateDto dto) {
        notNull(dto);

        val leader = userRepository.getById(dto.getLeaderId());

        val party = new Party(leader, dto.getName(), dto.getDescription());

        return partyService.create(party);
    }

    @Override
    public Pair<User, List<Party>> getParties(long userId) {
        val user = userRepository.getById(userId);

        val parties = partyService.getParties(user);

        return Pair.of(user, parties);
    }
}
