package com.maple.api.controller.dto;

import com.maple.common.party.domain.Party;

import java.time.OffsetDateTime;

public class PartyCreateDto {

    public record PartyCreateRequest(
            long leaderId,
            String name,
            String description
    ) {

        public com.maple.api.service.dto.PartyCreateDto toDto() {
            return new com.maple.api.service.dto.PartyCreateDto(this.leaderId, this.name, this.description);
        }
    }

    public record PartyCreateResponse(PartyCreateData party) {
    }

    public record PartyCreateData(
            long id,
            long leaderId,
            String leaderNickname,
            String name,
            String description,
            OffsetDateTime createAt
    ) {

        public static PartyCreateData create(Party party) {
            return new PartyCreateData(
                    party.getId(), party.getLeaderId(), party.getLeaderNickname(),
                    party.getName(), party.getDescription(), party.getCreateAt()
            );
        }
    }
}
