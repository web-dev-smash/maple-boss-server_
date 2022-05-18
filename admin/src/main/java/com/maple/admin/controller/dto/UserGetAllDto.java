package com.maple.admin.controller.dto;

import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.OffsetDateTime;
import java.util.List;

public class UserGetAllDto {

    @Getter
    @AllArgsConstructor
    public static class UserGetAllResponse {
        private List<UserGetAllData> users;
        private int totalPages;
        private long totalElements;
        private boolean last;

        public static UserGetAllResponse create(Page<User> users) {
            return new UserGetAllResponse(
                    UserGetAllData.create(users), users.getTotalPages(), users.getTotalElements(), users.isLast()
            );
        }
    }

    @Getter
    @AllArgsConstructor
    public static class UserGetAllData {
        private long id;
        private String loginId;
        private String nickname;
        private String email;
        private UserStatus status;
        private OffsetDateTime createAt;

        private UserGetAllData(User user) {
            this(user.getId(), user.getLoginId(), user.getNickname(),
                    user.getEmail(), user.getStatus(), user.getCreateAt());
        }

        private static List<UserGetAllData> create(Page<User> users) {
            return users.map(UserGetAllData::new).toList();
        }
    }
}
