package com.maple.admin.controller.dto;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyStatus;
import org.springframework.data.domain.Page;

import java.time.OffsetDateTime;
import java.util.List;

public class PartyGetAllDto {

    public record PartyGetAllResponse(
            List<PartyGetAllData> parties,
            int totalPages,
            long totalElements,
            boolean last
    ) {

        public static PartyGetAllResponse create(Page<Party> parties) {
            return new PartyGetAllResponse(
                    PartyGetAllData.create(parties), parties.getTotalPages(), parties.getTotalElements(), parties.isLast()
            );
        }
    }

    public record PartyGetAllData(
            long id,
            long leaderId,
            String leaderNickname,
            String name,
            String description,
            PartyStatus status,
            OffsetDateTime createAt
    ) {

        private PartyGetAllData(Party party) {
            this(
                    party.getId(), party.getLeaderId(), party.getLeaderNickname(),
                    party.getName(), party.getDescription(),
                    party.getStatus(), party.getCreateAt()
            );
        }

        public static List<PartyGetAllData> create(Page<Party> parties) {
            return parties.map(PartyGetAllData::new).toList();
        }
    }
}
