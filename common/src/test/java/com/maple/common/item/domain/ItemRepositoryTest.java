package com.maple.common.item.domain;

import com.maple.common.fixture.ItemFixture;
import com.maple.common.support.BaseRepositoryTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void 아이템_생성_성공() {
        var item = ItemFixture.createItem();

        item = itemRepository.save(item);

        assertThat(item.getId()).isNotNull();

        val foundItem = itemRepository.findById(item.getId()).orElseThrow();

        assertThat(foundItem.getName()).isEqualTo("태초의정수");
        assertThat(foundItem.getType()).isEqualTo(ItemType.EXTRA);
        assertThat(foundItem.getStatus()).isEqualTo(ItemStatus.CREATED);
        assertThat(foundItem.getCreateAt()).isNotNull();
    }
}