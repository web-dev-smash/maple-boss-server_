package com.maple.admin.controller;

import com.maple.admin.controller.dto.ItemCreateDto.ItemCreateData;
import com.maple.admin.controller.dto.ItemCreateDto.ItemCreateRequest;
import com.maple.admin.controller.dto.ItemCreateDto.ItemCreateResponse;
import com.maple.common.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemApi {

    private final ItemService itemService;

    /**
     * 아이템 생성
     */
    @PostMapping
    public ItemCreateResponse createItem(@RequestBody ItemCreateRequest req) {
        val item = itemService.create(req.toEntity());

        return new ItemCreateResponse(ItemCreateData.create(item));
    }
}
