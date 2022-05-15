package com.maple.common.item.domain;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

class ItemTest {

    @Test
    void 아이템_생성_성공() {
        val item = new Item("태초의정수", ItemType.EXTRA);

        assertThat(item.getName()).isEqualTo("태초의정수");
        assertThat(item.getType()).isEqualTo(ItemType.EXTRA);
        assertThat(item.getStatus()).isEqualTo(ItemStatus.CREATED);
        assertThat(item.getCreateAt()).isNotNull();
    }

    @Test
    void 아이템_생성_실패__아이템_타입이_null() {
        assertThatNullPointerException().isThrownBy(() -> new Item("태초의정수", null));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 아이템_생성_실패__아이템_이름이_null(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Item(name, ItemType.EXTRA));
    }

    @Test
    void 아이템_생성_실패__아이템_이름이_공백() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Item(" ", ItemType.EXTRA));
    }
}
