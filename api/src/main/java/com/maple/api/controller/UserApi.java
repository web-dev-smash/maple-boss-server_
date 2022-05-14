package com.maple.api.controller;

import com.maple.api.service.UserAppService;
import com.maple.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static com.maple.api.controller.dto.UserCreateDto.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    private final UserAppService userAppService;

    /**
     * 유저 가입
     */
    @PostMapping
    public UserCreateResponse create(@RequestBody UserCreateRequest req) {
        val user = userAppService.create(req.toDto());

        return new UserCreateResponse(UserCreateData.create(user));
    }

    /**
     * 유저 탈퇴 준비
     * TODO: Security 이후 로그인한 유저 ID가 leader ID로 대체
     */
    @PostMapping("/{id}/prepare-withdrawal")
    public void prepareWithdrawal(@PathVariable Long id) {
        userService.prepareWithdrawal(id);
    }
}
