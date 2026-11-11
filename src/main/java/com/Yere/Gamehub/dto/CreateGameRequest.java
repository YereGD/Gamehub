package com.Yere.Gamehub.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateGameRequest(
    @NotBlank (message = "Game is mandatory") String name
) {}
