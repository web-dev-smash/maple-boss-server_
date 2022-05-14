package com.maple.api.controller;

import com.maple.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    /**
     * 회원 탈퇴 준비
     * TODO: Security 이후 로그인한 유저 ID가 leader ID로 대체
     */
    @PostMapping("/{id}/prepare-withdrawal")
    public void prepareWithdrawal(@PathVariable Long id) {
        userService.prepareWithdrawal(id);
    }
}
