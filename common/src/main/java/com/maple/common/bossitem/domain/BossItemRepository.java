package com.maple.common.bossitem.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BossItemRepository extends JpaRepository<BossItem, Long> {

    @Query("""
            select f
            from FixedBossItem f
             """)
    List<FixedBossItem> findAllFixedBossItem();

    @Query("""
            select r
            from RandomBossItem r
            """)
    List<RandomBossItem> findAllRandomBossItem();

    @Query("""
            select f
            from FixedBossItem f
            where f.id = :id
            """)
    Optional<FixedBossItem> findFixedBossItem(long id);

    @Query("""
            select r
            from RandomBossItem r
            where r.id = :id
            """)
    Optional<RandomBossItem> findRandomBossItem(long id);
}
