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
        val item = itemRepository.save(ItemFixture.createItem());

        assertThat(item.getId()).isNotNull();

        val foundItem = itemRepository.findById(item.getId()).orElseThrow();

        assertThat(foundItem).isEqualTo(item);
    }
}