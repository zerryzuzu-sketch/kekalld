package com.interview.lld.service;

import com.interview.lld.dto.ItemRequest;
import com.interview.lld.dto.ItemResponse;

import java.util.List;

public interface ItemService {

    ItemResponse create(ItemRequest request);

    ItemResponse getById(String id);

    List<ItemResponse> getAll();

    void delete(String id);
}
