package com.Yere.Gamehub.repository;

import java.util.List;
import java.util.Optional;

import com.Yere.Gamehub.model.Game;

public interface GameRepository {

    List<Game> findAll();

    Optional<Game> findById(int id);

    Game save(Game game);

    void deleteById(int id);
}
