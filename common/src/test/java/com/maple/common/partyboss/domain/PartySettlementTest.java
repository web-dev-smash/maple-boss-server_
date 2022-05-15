package com.maple.common.partyboss.domain;

import com.maple.common.boss.domain.Boss;
import com.maple.common.bossitem.domain.RandomBossItem;
import com.maple.common.item.domain.Item;
import com.maple.common.party.domain.Party;
import com.maple.common.user.domain.User;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.maple.common.fixture.BossFixture.createBoss;
import static com.maple.common.fixture.BossItemFixture.createRandomBossItem;
import static com.maple.common.fixture.ItemFixture.createItem;
import static com.maple.common.partyboss.domain.PartySettlementStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PartySettlementTest {

    private User leader;
    private User member;
    private Item item;
    private Boss boss;
    private RandomBossItem randomBossItem;

    @BeforeEach
    void setUp() {
        leader = mock(User.class);
        member = mock(User.class);

        given(leader.getId()).willReturn(1L);
        given(member.getId()).willReturn(2L);

        item = createItem();

        boss = createBoss();
        randomBossItem = createRandomBossItem(boss, item);
    }

    @Test
    void 파티보스정산_생성() {
        val partyBossItem = new PartySettlementItem(randomBossItem, 1, 3000);

        val leaderDividend = new PartySettlementDividend(leader, BigDecimal.valueOf(60));
        val memberDividend = new PartySettlementDividend(member, BigDecimal.valueOf(40));

        val party = mock(Party.class);

        given(party.getId()).willReturn(1L);

        party.addMember(member);

        val partyBoss = new PartySettlement(party, boss, List.of(partyBossItem), List.of(leaderDividend, memberDividend));

        assertThat(partyBoss.getParty()).isEqualTo(party);
        assertThat(partyBoss.getBoss()).isEqualTo(boss);
        assertThat(partyBoss.getItems()).containsExactly(partyBossItem);
        assertThat(partyBoss.getDividends()).containsExactly(leaderDividend, memberDividend);
        assertThat(partyBoss.getStatus()).isEqualTo(CREATED);
        assertThat(partyBoss.getCreateAt()).isNotNull();
    }
}