package com.Yere.Gamehub.mapper;

import com.Yere.Gamehub.model.Game;
import com.Yere.Gamehub.model.GameEntity;

public final class GameEntityMapper {
    private GameEntityMapper() {
        // Private constructor to prevent instantiation
    }
    public static Game toGame(GameEntity gameEntity) {
        return new Game(gameEntity.getName(), gameEntity.getId());
    }

    public static GameEntity toEntity(Game game) {
    return new GameEntity(game.name());
    }

    public static void updateEntity(GameEntity gameEntity, Game game) {
        gameEntity.setName(game.name());
    }  
}
