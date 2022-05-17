package com.maple.admin.controller.dto;

import com.maple.common.party.domain.Party;
import com.maple.common.party.domain.PartyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.OffsetDateTime;
import java.util.List;

public class PartyGetAllDto {

    @Getter
    @AllArgsConstructor
    public static class PartyGetAllResponse {
        private List<PartyGetAllData> parties;
        private int totalPages;
        private long totalElements;
        private boolean last;

        public static PartyGetAllResponse create(Page<Party> parties) {
            return new PartyGetAllResponse(
                    PartyGetAllData.create(parties), parties.getTotalPages(), parties.getTotalElements(), parties.isLast()
            );
        }
    }

    @Getter
    @AllArgsConstructor
    public static class PartyGetAllData {
        private long id;
        private long leaderId;
        private String leaderNickname;
        private String name;
        private String description;
        private PartyStatus status;
        private OffsetDateTime createAt;

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
