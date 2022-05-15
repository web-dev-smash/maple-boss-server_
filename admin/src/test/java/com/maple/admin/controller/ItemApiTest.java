package com.maple.admin.controller;

import com.maple.admin.controller.dto.ItemCreateDto.ItemCreateRequest;
import com.maple.admin.support.BaseApiTest;
import com.maple.common.item.domain.ItemType;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ItemApiTest extends BaseApiTest {

    @Test
    void 아이템_생성_성공() throws Exception {
        val req = new ItemCreateRequest("태초의정수", ItemType.EXTRA);

        mockMvc.perform(post("/item")
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.item.id").isNotEmpty(),
                        jsonPath("$.item.name").value("태초의정수"),
                        jsonPath("$.item.type").value(ItemType.EXTRA.name()),
                        jsonPath("$.item.createAt").isNotEmpty()
                );
    }
}