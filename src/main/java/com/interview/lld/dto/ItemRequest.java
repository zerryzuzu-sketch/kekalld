package com.interview.lld.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemRequest {

    @NotBlank(message = "must not be blank")
    private String name;

    @Positive(message = "must be positive")
    private double price;
}
