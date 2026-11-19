package com.Yere.Gamehub.repository;

import java.util.List;
import java.util.Optional;

import com.Yere.Gamehub.model.Game;

public interface GameRepository {

    List<Game> findAll();

    Optional<Game> findById(Long id);

    Optional<Game> update(Game game);
    
    Game save(Game game);

    boolean deleteById(Long id);
}
