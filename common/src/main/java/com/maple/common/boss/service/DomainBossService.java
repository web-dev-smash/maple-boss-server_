package com.maple.common.boss.service;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossRepository;
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

    @Override
    public Boss findById(Long id) {
        return bossRepository.findById(id).orElseThrow();
    }
}
