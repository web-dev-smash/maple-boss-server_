package com.maple.api.controller.dto;

import com.maple.common.party.domain.Party;
import com.maple.common.user.domain.User;
import org.springframework.data.util.Pair;

import java.time.OffsetDateTime;
import java.util.List;

public class PartyDto {

    public record PartyResponses(List<PartyData> parties) {
    }

    public record PartyData(
            long id,
            long leaderId,
            String leaderNickname,
            String name,
            String description,
            boolean isLeader,
            OffsetDateTime createAt
    ) {

        public static List<PartyData> create(Pair<User, List<Party>> pair) {
            return pair.getSecond().stream()
                    .map(it -> PartyData.create(it, pair.getFirst()))
                    .toList();
        }

        public static PartyData create(Party party, User user) {
            return new PartyData(
                    party.getId(), party.getLeaderId(), party.getLeaderNickname(),
                    party.getName(), party.getDescription(), party.isLeader(user), party.getCreateAt()
            );
        }
    }
}
