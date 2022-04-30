package com.maple.common.item.domain;

import com.maple.common.fixture.ItemFixture;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void 아이템_생성_성공() {
        var item = ItemFixture.createItem();

        item = itemRepository.save(item);

        assertThat(item.getId()).isNotNull();

        val findItem = itemRepository.findById(item.getId()).orElseThrow();

        assertThat(findItem.getName()).isEqualTo("태초의정수");
        assertThat(findItem.getType()).isEqualTo(ItemType.EXTRA);
        assertThat(findItem.getStatus()).isEqualTo(ItemStatus.CREATED);
        assertThat(findItem.getCreateAt()).isNotNull();
    }
}