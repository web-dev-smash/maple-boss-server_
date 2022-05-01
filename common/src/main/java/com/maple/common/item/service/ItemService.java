package com.maple.common.item.service;

import com.maple.common.item.domain.Item;

public interface ItemService {

    /**
     * 아이템 생성
     * status = CREATED
     */
    Item create(Item item);
}
