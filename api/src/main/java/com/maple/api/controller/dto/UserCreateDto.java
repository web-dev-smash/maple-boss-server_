package com.maple.api.controller.dto;

import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserStatus;

import java.time.OffsetDateTime;

public class UserCreateDto {

    public record UserCreateRequest(
            String loginId,
            String password,
            String nickname,
            String email
    ) {

        public com.maple.api.service.dto.UserCreateDto toDto() {
            return new com.maple.api.service.dto.UserCreateDto(this.loginId, this.password, this.nickname, this.email);
        }
    }

    public record UserCreateResponse(UserCreateData user) {
    }

    public record UserCreateData(
            long id,
            String loginId,
            String nickname,
            String email,
            UserStatus status,
            OffsetDateTime createAt
    ) {

        public static UserCreateData create(User user) {
            return new UserCreateData(
                    user.getId(), user.getLoginId(), user.getNickname(), user.getEmail(), user.getStatus(), user.getCreateAt()
            );
        }
    }
}
