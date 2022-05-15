package com.maple.admin.service;

import com.maple.admin.service.dto.FixedBossItemCreateDto;
import com.maple.admin.service.dto.RandomBossItemCreateDto;
import com.maple.admin.support.BaseServiceTest;
import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.service.BossService;
import com.maple.common.item.domain.Item;
import com.maple.common.item.service.ItemService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.maple.admin.fixture.BossFixture.createBoss;
import static com.maple.admin.fixture.BossItemFixture.createFixedBossAmount;
import static com.maple.admin.fixture.BossItemFixture.createRandomBossItemAmount;
import static com.maple.admin.fixture.ItemFixture.createItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class DefaultBossItemAdminServiceTest extends BaseServiceTest {

    @Autowired
    private BossItemAdminService bossItemAdminService;

    @Autowired
    private BossService bossService;

    @Autowired
    private ItemService itemService;

    private Boss boss;
    private Item item;


    @BeforeEach
    void setUp() {
        boss = bossService.create(createBoss());
        item = itemService.create(createItem());
    }

    @Test
    void 고정_보스_아이템_생성_성공() {
        val amount = createFixedBossAmount();
        val dto = new FixedBossItemCreateDto(boss.getId(), item.getId(), amount, 1000L);

        val fixedBossItem = bossItemAdminService.createFixedBossItem(dto);

        assertThat(fixedBossItem.getBoss()).isEqualTo(boss);
        assertThat(fixedBossItem.getItem()).isEqualTo(item);
        assertThat(fixedBossItem.getAmount()).isEqualTo(amount);
        assertThat(fixedBossItem.getMeso()).isEqualTo(1000L);
    }

    @Test
    void 고정_보스_아이템_생성_실패__고정_보스_아이템_dto가_null() {
        assertThatNullPointerException().isThrownBy(() -> bossItemAdminService.createFixedBossItem(null));
    }

    @Test
    void 랜덤_보스_아이템_생성_성공() {
        val amount = createRandomBossItemAmount();
        val dto = new RandomBossItemCreateDto(boss.getId(), item.getId(), amount);

        val randomBossItem = bossItemAdminService.createRandomBossItem(dto);

        assertThat(randomBossItem.getBoss()).isEqualTo(boss);
        assertThat(randomBossItem.getItem()).isEqualTo(item);
        assertThat(randomBossItem.getAmount()).isEqualTo(amount);
    }

    @Test
    void 랜덤_보스_아이템_생성_실패__랜덤_보스_아이템_dto가_null() {
        assertThatNullPointerException().isThrownBy(() -> bossItemAdminService.createRandomBossItem(null));
    }
}