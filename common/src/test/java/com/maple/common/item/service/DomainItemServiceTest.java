package com.maple.common.item.service;

import com.maple.common.fixture.ItemFixture;
import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemRepository;
import com.maple.common.item.domain.ItemType;
import com.maple.core.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class DomainItemServiceTest extends BaseServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

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
    void 아이템_생성_성공() {
        var item = new Item("태초의정수", ItemType.EXTRA);

        item = itemService.create(item);

        assertThat(item.getId()).isNotNull();

        val foundItem = itemRepository.findById(item.getId()).orElseThrow();

        assertThat(foundItem.getName()).isEqualTo("태초의정수");
        assertThat(foundItem.getType()).isEqualTo(ItemType.EXTRA);
    }

    @Test
    void 아이템_생성_실패__아이템이_null() {
        assertThatNullPointerException().isThrownBy(() -> itemService.create(null));
    }

    @Test
    void 아이템_목록_조회() {
        val items = itemService.getItems();

        assertThat(items).containsExactly(item1, item2, item3);
    }
}
