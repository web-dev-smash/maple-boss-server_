package com.maple.domain.boss.service;

import com.maple.domain.boss.domain.Boss;
import com.maple.domain.boss.domain.BossRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainBossService implements BossService {

    private final BossRepository bossRepository;

    @Override
    public Boss create(final Boss boss) {
        return bossRepository.save(boss);
    }
}
