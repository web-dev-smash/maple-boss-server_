package com.maple.api.controller.dto;

import com.maple.common.party.domain.Party;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

public class PartyCreateDto {

    @Getter
    @AllArgsConstructor
    public static class PartyCreateRequest {
        private long leaderId;
        private String name;
        private String description;

        public com.maple.api.service.dto.PartyCreateDto toDto() {
            return new com.maple.api.service.dto.PartyCreateDto(this.leaderId, this.name, this.description);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class PartyCreateResponse {
        private PartyCreateData party;
    }

    @Getter
    @AllArgsConstructor
    public static class PartyCreateData {
        private long id;
        private long leaderId;
        private String leaderNickname;
        private String name;
        private String description;
        private OffsetDateTime createAt;

        public static PartyCreateData create(Party party) {
            return new PartyCreateData(
                    party.getId(), party.getLeaderId(), party.getLeaderNickname(),
                    party.getName(), party.getDescription(), party.getCreateAt()
            );
        }
    }
}
