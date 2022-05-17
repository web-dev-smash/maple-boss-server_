package com.maple.common.party.domain;

import com.maple.common.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {

    @Query(
            "select p "+
            "from Party p "+
            "join fetch p.members m "+
            "where m = :user"
            )
    List<Party> findAllParty(User user);
}
