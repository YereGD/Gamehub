package com.Yere.Gamehub.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record Game(
    @NotBlank(message = "Name is mandatory") String name,
    @Min(value = 1, message = "ID must be a positive integer") int id
    ) {}

