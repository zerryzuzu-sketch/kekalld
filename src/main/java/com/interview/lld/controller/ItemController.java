package com.interview.lld.controller;

import com.interview.lld.common.ApiResponse;
import com.interview.lld.dto.ItemRequest;
import com.interview.lld.dto.ItemResponse;
import com.interview.lld.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ItemResponse> create(@Valid @RequestBody ItemRequest request) {
        return ApiResponse.ok(itemService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<ItemResponse> getById(@PathVariable String id) {
        return ApiResponse.ok(itemService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<ItemResponse>> getAll() {
        return ApiResponse.ok(itemService.getAll());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        itemService.delete(id);
    }
}
