package com.maple.api.controller.dto;

import com.maple.common.party.domain.Party;
import com.maple.common.user.domain.User;
import org.springframework.data.util.Pair;

import java.time.OffsetDateTime;
import java.util.List;

public class PartyGetAllDto {

    public record PartyGetAllResponse(List<PartyGetAllData> parties) {
    }

    public record PartyGetAllData(
            long id,
            long leaderId,
            String leaderNickname,
            String name,
            String description,
            boolean isLeader,
            OffsetDateTime createAt
    ) {

        private PartyGetAllData(Party party, User user) {
            this(
                    party.getId(), party.getLeaderId(), party.getLeaderNickname(),
                    party.getName(), party.getDescription(), party.isLeader(user), party.getCreateAt()
            );
        }

        public static List<PartyGetAllData> create(Pair<User, List<Party>> pair) {
            return pair.getSecond().stream()
                    .map(it -> new PartyGetAllData(it, pair.getFirst()))
                    .toList();
        }
    }
}
