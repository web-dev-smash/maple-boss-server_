package com.maple.api.controller.dto;

import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

public class UserCreateDto {

    @Getter
    @AllArgsConstructor
    public static class UserCreateRequest {
        private String loginId;
        private String password;
        private String nickname;
        private String email;

        public com.maple.api.service.dto.UserCreateDto toDto() {
            return new com.maple.api.service.dto.UserCreateDto(this.loginId, this.password, this.nickname, this.email);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class UserCreateResponse {
        private UserCreateData user;
    }

    @Getter
    @AllArgsConstructor
    public static class UserCreateData {
        private long id;
        private String loginId;
        private String nickname;
        private String email;
        private UserStatus status;
        private OffsetDateTime createdAt;

        public static UserCreateData create(User user) {
            return new UserCreateData(
                    user.getId(), user.getLoginId(), user.getNickname(), user.getEmail(), user.getStatus(), user.getCreatedAt()
            );
        }
    }
}
