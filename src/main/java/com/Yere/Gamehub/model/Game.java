package com.Yere.Gamehub.model;

import jakarta.validation.constraints.NotBlank;

public record Game(
    @NotBlank(message = "Name is mandatory") String name,
    Long id
    ) {}

