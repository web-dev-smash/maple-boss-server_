package com.maple.common.fixture;

import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemType;

public class ItemFixture {

    public static Item createItem() {
        return new Item("태초의정수", ItemType.EXTRA);
    }
}
