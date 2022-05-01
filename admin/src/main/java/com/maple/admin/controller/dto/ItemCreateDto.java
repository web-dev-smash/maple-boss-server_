package com.maple.admin.controller.dto;

import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

public class ItemCreateDto {

    @Getter
    @AllArgsConstructor
    public static class ItemCreateRequest {

        private String name;
        private ItemType type;

        public Item toEntity() {
            return new Item(name, type);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class ItemCreateResponse {

        private ItemCreateData item;
    }

    @Getter
    @AllArgsConstructor
    public static class ItemCreateData {

        private long id;
        private String name;
        private ItemType type;
        private OffsetDateTime createAt;

        public static ItemCreateData create(Item item) {
            return new ItemCreateData(item.getId(), item.getName(), item.getType(), item.getCreateAt());
        }
    }
}
