package com.Yere.Gamehub.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatedGameRequest(@NotBlank String name) {
    
}
