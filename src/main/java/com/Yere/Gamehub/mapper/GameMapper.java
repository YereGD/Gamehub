package com.Yere.Gamehub.mapper;

import com.Yere.Gamehub.dto.CreateGameRequest;
import com.Yere.Gamehub.dto.GameResponse;
import com.Yere.Gamehub.dto.UpdatedGameRequest;
import com.Yere.Gamehub.model.Game;

public final class GameMapper {
    private GameMapper() {
        // Private constructor to prevent instantiation
    }

    public static GameResponse toResponse(Game game) {
        return new GameResponse(game.id(), game.name());
    }

    public static Game toGame(CreateGameRequest request) {
        return new Game(request.name(), null);
    }

    public static Game toGame(UpdatedGameRequest request, Long id) {
        return new Game(request.name(), id);
    }
    
}
