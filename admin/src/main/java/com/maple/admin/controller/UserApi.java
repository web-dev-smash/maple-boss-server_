package com.maple.admin.controller;

import com.maple.admin.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maple.admin.controller.dto.UserGetAllDto.UserGetAllResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApi {

    private final UserAdminService userAdminService;

    /**
     * 전체 유저 목록 조회
     */
    @GetMapping
    public UserGetAllResponse getAllUsers(Pageable pageable) {
        val getUsers = userAdminService.getAllUsers(pageable);

        return UserGetAllResponse.create(getUsers);
    }
}
