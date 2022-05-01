package com.maple.admin.controller.dto;

import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemType;

import java.time.OffsetDateTime;

public class ItemCreateDto {

    public record ItemCreateRequest(
            String name,
            ItemType type
    ) {
        public Item toEntity() {
            return new Item(name, type);
        }
    }

    public record ItemCreateResponse(ItemCreateData item) {
    }

    public record ItemCreateData(
            long id,
            String name,
            ItemType type,
            OffsetDateTime createAt
    ) {
        public static ItemCreateData create(Item item) {
            return new ItemCreateData(item.getId(), item.getName(), item.getType(), item.getCreateAt());
        }
    }
}
