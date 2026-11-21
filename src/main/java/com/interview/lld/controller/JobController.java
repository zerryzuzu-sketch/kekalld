package com.interview.lld.controller;


import com.interview.lld.common.ApiResponse;
import com.interview.lld.dto.ItemRequest;
import com.interview.lld.dto.ItemResponse;
import com.interview.lld.dto.JobRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ItemResponse> createJob(@Valid @RequestBody JobRequest request) {
        //return ApiResponse.ok(itemService.create(request));
    }
}
