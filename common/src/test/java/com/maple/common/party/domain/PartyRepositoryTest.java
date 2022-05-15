package com.maple.common.party.domain;

import com.maple.common.support.BaseRepositoryTest;
import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.MockCertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static com.maple.common.fixture.PartyFixture.createParty;
import static com.maple.common.fixture.UserFixture.createUser;
import static org.assertj.core.api.Assertions.assertThat;

@Import(MockCertCodeGenerator.class)
class PartyRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertCodeGenerator mockCertCodeGenerator;

    private User leader;
    private User otherLeader;
    private User member;

    @BeforeEach
    void setUp() {
        leader = createUser();
        leader.prepareCertCode(mockCertCodeGenerator);
        leader = userRepository.save(leader);

        otherLeader = new User("otherLeader", "1234", "다른리더", "다른리더");
        otherLeader.prepareCertCode(mockCertCodeGenerator);
        otherLeader = userRepository.save(otherLeader);

        member = new User("member", "1234", "member", "member");
        member.prepareCertCode(mockCertCodeGenerator);
        member = userRepository.save(member);
    }

    @Test
    void 파티_생성_성공() {
        val party = partyRepository.save(createParty(leader));

        assertThat(party.getId()).isNotNull();

        val foundParty = partyRepository.findById(party.getId()).orElseThrow();

        assertThat(foundParty).isEqualTo(party);
    }

    @Test
    void 나의_파티목록_조회__파티원() {
        val party1 = partyRepository.save(createParty(leader));
        val party2 = partyRepository.save(createParty(leader));
        val party3 = partyRepository.save(createParty(leader));
        val otherParty1 = partyRepository.save(createParty(leader));
        val otherParty2 = partyRepository.save(createParty(leader));

        party1.addMember(member);
        party2.addMember(member);
        party3.addMember(member);

        val parties = partyRepository.findAllParty(member);

        assertThat(parties).containsExactly(party1, party2, party3);
        assertThat(parties).doesNotContain(otherParty1, otherParty2);
    }

    @Test
    void 나의_파티목록_조회__파티장() {
        val party1 = partyRepository.save(createParty(leader));
        val party2 = partyRepository.save(createParty(leader));
        val party3 = partyRepository.save(createParty(leader));
        val otherParty1 = partyRepository.save(createParty(otherLeader));
        val otherParty2 = partyRepository.save(createParty(otherLeader));

        party1.addMember(member);
        party2.addMember(member);
        party3.addMember(member);

        val parties = partyRepository.findAllParty(leader);

        assertThat(parties).containsExactly(party1, party2, party3);
        assertThat(parties).doesNotContain(otherParty1, otherParty2);
    }

    @Test
    void 나의_파티목록_조회__파티장이면서_다른_파티에_참여() {
        val party1 = partyRepository.save(createParty(leader));
        val party2 = partyRepository.save(createParty(leader));
        val party3 = partyRepository.save(createParty(leader));
        val otherParty1 = partyRepository.save(createParty(otherLeader));
        val otherParty2 = partyRepository.save(createParty(otherLeader));

        party1.addMember(member);
        party2.addMember(member);
        party3.addMember(member);
        otherParty1.addMember(leader);
        otherParty2.addMember(leader);

        val parties = partyRepository.findAllParty(leader);

        assertThat(parties).containsExactly(party1, party2, party3, otherParty1, otherParty2);
        assertThat(parties)
                .extracting(party -> party.isLeader(leader))
                .containsExactly(true, true, true, false, false);
    }
}