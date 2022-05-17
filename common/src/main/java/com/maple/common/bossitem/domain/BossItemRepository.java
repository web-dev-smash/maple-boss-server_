package com.maple.common.bossitem.domain;

import com.maple.common.boss.domain.Boss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BossItemRepository extends JpaRepository<BossItem, Long> {

    @Query(
            "select b "+
            "from BossItem b "+
            "where b.boss = :boss"
            )
    List<BossItem> findAll(Boss boss);

    @Query(
            "select f "+
            "from FixedBossItem f "+
            "where f.boss = :boss"
            )
    List<FixedBossItem> findAllFixedBossItem(Boss boss);

    @Query(
            "select r from RandomBossItem r where r.boss = :boss"
            )
    List<RandomBossItem> findAllRandomBossItem(Boss boss);

    @Query(
            "select f "+
            "from FixedBossItem f "+
            "where f.id = :id"
            )
    Optional<FixedBossItem> findFixedBossItem(long id);

    @Query(
            "select r "+
            "from RandomBossItem r "+
            "where r.id = :id"
            )
    Optional<RandomBossItem> findRandomBossItem(long id);
}
