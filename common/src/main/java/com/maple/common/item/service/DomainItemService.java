package com.maple.common.item.service;

import com.maple.common.item.domain.Item;
import com.maple.common.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.maple.core.exception.Preconditions.notNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainItemService implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item create(Item item) {
        notNull(item);

        return itemRepository.save(item);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }
}
