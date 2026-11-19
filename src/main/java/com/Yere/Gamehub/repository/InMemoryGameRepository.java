package com.Yere.Gamehub.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.Yere.Gamehub.model.Game;


public class InMemoryGameRepository implements GameRepository {

    private final List<Game> games = new ArrayList<>(List.of(
        new Game("Valorant", 1L),
        new Game("Minecraft", 2L),
        new Game("League of Legends", 3L)
    ));

    @Override
    public List<Game> findAll() {
        return games;
    }

    @Override
    public Optional<Game> findById(Long id) {
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
    public boolean deleteById(Long id) {
        return games.removeIf(game -> game.id() == id);
    }
    
    // The update method is not implemented in this in-memory repository cause it was added after the initial implementation. It should be implemented to update an existing game in the list.
    @Override
    public Optional<Game> update(Game game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}