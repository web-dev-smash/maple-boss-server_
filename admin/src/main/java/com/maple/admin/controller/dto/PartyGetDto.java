package com.maple.admin.controller.dto;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyStatus;
import org.springframework.data.domain.Page;

import java.time.OffsetDateTime;
import java.util.List;

public class PartyGetDto {

    public record PartiesGetResponse(
            List<PartiesGetData> parties,
            int totalPages,
            long totalElements,
            boolean last
    ) {

        public static PartiesGetResponse create(Page<Party> parties) {
            return new PartiesGetResponse(
                    PartiesGetData.create(parties), parties.getTotalPages(), parties.getTotalElements(), parties.isLast()
            );
        }
    }

    public record PartiesGetData(
            long id,
            long leaderId,
            String leaderNickname,
            String name,
            String description,
            PartyStatus status,
            OffsetDateTime createAt
    ) {

        public static List<PartiesGetData> create(Page<Party> parties) {
            return parties.map(PartiesGetData::create).stream().toList();
        }

        private static PartiesGetData create(Party party) {
            return new PartiesGetData(
                    party.getId(), party.getLeaderId(), party.getLeaderNickname(),
                    party.getName(), party.getDescription(),
                    party.getStatus(), party.getCreateAt()
            );
        }
    }
}
