package com.maple.common.item.service;

import com.maple.common.item.domain.Item;

import java.util.List;

public interface ItemService {

    /**
     * 아이템 생성
     * status = CREATED
     */
    Item create(Item item);

    /**
     * 아이템 전체 조회
     */
    List<Item> getItems();
}
