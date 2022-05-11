package com.maple.admin.fixture;

import com.maple.common.party.domain.Party;
import com.maple.common.user.domain.User;

public class PartyFixture {

    public static Party createParty(User leader) {
        return new Party(leader, "파티", "파티 설명");
    }
}
