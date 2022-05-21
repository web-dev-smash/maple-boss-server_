package com.maple.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserPrepareWithdrawalDto {

    @Getter
    @AllArgsConstructor
    public static class UserPrepareWithdrawalRequest {
        private long id;
        private String certCode;

        public com.maple.api.service.dto.UserPrepareWithdrawalDto toDto() {
            return new com.maple.api.service.dto.UserPrepareWithdrawalDto(this.id, this.certCode);
        }
    }
}
