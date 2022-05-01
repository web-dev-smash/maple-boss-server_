package com.maple.common.item.service;

import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultItemService implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item create(Item item) {
        checkNotNull(item, "아이템은 필수입니다.");

        return itemRepository.save(item);
    }
}
