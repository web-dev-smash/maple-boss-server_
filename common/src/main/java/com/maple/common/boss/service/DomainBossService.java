package com.maple.common.boss.service;

import com.maple.common.boss.domain.Boss;
import com.maple.common.boss.domain.BossRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Boss getBoss(Long id) {
        return bossRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Boss> getBosses() {
        return bossRepository.findAll();
    }
}
