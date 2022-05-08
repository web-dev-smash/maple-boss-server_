package com.maple.admin.controller;

import com.maple.admin.support.BaseApiTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * API 스펙 토대로 ApiAdvice 테스트
 *
 * @see TestApi
 */
class ApiAdviceTest extends BaseApiTest {

    @Test
    void 예외발생() throws Exception {
        mockMvc.perform(get("/test/ex"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.error.message").value("예상치 못한 에러가 발생했습니다.")
                );
    }

    @Test
    void 메이플보스_예외발생() throws Exception {
        mockMvc.perform(get("/test/maple-boss-ex"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.error.message").value("헬로우 월드!!!")
                );
    }
}