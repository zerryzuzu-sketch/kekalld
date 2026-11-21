package com.interview.lld.service.impl;

import com.interview.lld.dto.ItemRequest;
import com.interview.lld.dto.ItemResponse;
import com.interview.lld.entity.Item;
import com.interview.lld.exception.ResourceNotFoundException;
import com.interview.lld.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ItemServiceImpl implements ItemService {

    private final Map<String, Item> store = new ConcurrentHashMap<>();

    @Override
    public ItemResponse create(ItemRequest request) {
        Item item = Item.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .price(request.getPrice())
                .build();
        store.put(item.getId(), item);
        return ItemResponse.from(item);
    }

    @Override
    public ItemResponse getById(String id) {
        Item item = store.get(id);
        if (item == null) {
            throw new ResourceNotFoundException("Item not found: " + id);
        }
        return ItemResponse.from(item);
    }

    @Override
    public List<ItemResponse> getAll() {
        return store.values().stream()
                .map(ItemResponse::from)
                .toList();
    }

    @Override
    public void delete(String id) {
        getById(id);
        store.remove(id);
    }
}
