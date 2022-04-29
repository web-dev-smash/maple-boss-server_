package com.maple.common.boss.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BossFixItemTest {


    @Test
    void 보스_고정아이템_생성() {
        BossFixItem bossFixItem = new BossFixItem("수에큐", 0, 4, "");

        assertThat(bossFixItem.getItemName()).isEqualTo("수에큐");
        assertThat(bossFixItem.getPrice()).isEqualTo(0);
        assertThat(bossFixItem.getCount()).isEqualTo(4);
        assertThat(bossFixItem.getManyCount()).isEqualTo("");
    }

    @Test
    // TODO : Junit 테스트는 안댐? 꼭 어썰트제이?
    void 보스_고정아이템_이름이_null_혹은_공백이면_실패(){
        assertThatIllegalArgumentException().isThrownBy(()->new BossFixItem("", 0, 4, ""));
        assertThatIllegalArgumentException().isThrownBy(()->new BossFixItem(null, 0, 4, ""));
        assertThrows(IllegalArgumentException.class, () -> new BossFixItem("", 0, 4, ""));
        assertThrows(IllegalArgumentException.class, () -> new BossFixItem(null, 0, 4, ""));
    }

    @Test
    void 보스_고정아이템_갯수가_음수가_오면_실패(){
        int negativeCount = -1;
        assertThatIllegalArgumentException().isThrownBy(()-> new BossFixItem("수에큐", 0, negativeCount, ""));
    }

    @Test
    void 보스_고정아이템_갯수가_0이오면_실패(){
        int negativeCount = 0;
        assertThatIllegalArgumentException().isThrownBy(()-> new BossFixItem("수에큐", 0, negativeCount, ""));

    }


    @Test
    void 보스_고정아이템_가격이_음수가_오면_실패(){
        int negativeCount = -1;
        assertThatIllegalArgumentException().isThrownBy(()-> new BossFixItem("수에큐숭", negativeCount, 1, ""));
    }





}