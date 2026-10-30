package com.Yere.Gamehub.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.Yere.Gamehub.model.Game;

@Repository 

public class InMemoryGameRepository implements GameRepository {

    private final List<Game> games = new ArrayList<>(List.of(
        new Game("Valorant", 1),
        new Game("Minecraft", 2),
        new Game("League of Legends", 3)
    ));

    @Override
    public List<Game> findAll() {
        return games;
    }

    @Override
    public Optional<Game> findById(int id) {
        return games.stream()
            .filter(game -> game.id() == id)
            .findFirst();
    }

    @Override
    public Game save(Game game) {
        games.removeIf(existingGame -> existingGame.id() == game.id());
        games.add(game);
        return game;
    }

    @Override
    public void deleteById(int id) {
        games.removeIf(game -> game.id() == id);
    }
}