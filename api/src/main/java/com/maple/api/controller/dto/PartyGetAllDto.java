package com.maple.api.controller.dto;

import com.maple.common.party.domain.Party;
import com.maple.common.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.util.Pair;

import java.time.OffsetDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PartyGetAllDto {

    @Getter
    @AllArgsConstructor
    public static class PartyGetAllResponse {
        private List<PartyGetAllData> parties;

    }

    @Getter
    @AllArgsConstructor
    public static class PartyGetAllData {
        private long id;
        private long leaderId;
        private String leaderNickname;
        private String name;
        private String description;
        private boolean isLeader;
        private OffsetDateTime createAt;

        private PartyGetAllData(Party party, User user) {
            this(
                    party.getId(), party.getLeaderId(), party.getLeaderNickname(),
                    party.getName(), party.getDescription(), party.isLeader(user), party.getCreateAt()
            );
        }

        public static List<PartyGetAllData> create(Pair<User, List<Party>> pair) {
            return pair.getSecond().stream()
                    .map(it -> new PartyGetAllData(it, pair.getFirst())).collect(toList());
        }
    }
}
