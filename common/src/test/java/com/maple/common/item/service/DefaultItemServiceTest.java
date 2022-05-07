package com.maple.common.item.service;

import com.maple.common.fixture.ItemFixture;
import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemType;
import com.maple.common.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class DefaultItemServiceTest extends BaseServiceTest {

    @Autowired
    private ItemService itemService;

    private Item item1;
    private Item item2;
    private Item item3;

    @BeforeEach
    void setUp() {
        item1 = itemService.create(ItemFixture.createItem());
        item2 = itemService.create(ItemFixture.createItem());
        item3 = itemService.create(ItemFixture.createItem());
    }

    @Test
    void 아이탬_생성_성공() {
        var item = ItemFixture.createItem();

        item = itemService.create(item);

        assertThat(item.getName()).isEqualTo("태초의정수");
        assertThat(item.getType()).isEqualTo(ItemType.EXTRA);
    }

    @Test
    void 아이템이_null_이면_생성_실패() {
        assertThatNullPointerException().isThrownBy(() -> itemService.create(null));
    }

    @Test
    void 아이템_목록_조회() {
        val items = itemService.getItems();

        assertThat(items).containsExactly(item1, item2, item3);
    }
}
