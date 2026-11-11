package com.Yere.Gamehub.repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.Yere.Gamehub.model.Game;
import com.Yere.Gamehub.model.GameEntity;
import com.Yere.Gamehub.repository.jpa.JpaGameRepository;


@Repository
public class PostgresGameRepository implements GameRepository {
    // Implementation of GameRepository methods for PostgreSQL database
    private final JpaGameRepository jpaGameRepository;

    public PostgresGameRepository(JpaGameRepository jpaGameRepository) {
        this.jpaGameRepository = jpaGameRepository;
    }   

    @Override 
    public List<Game> findAll() {
        return jpaGameRepository.findAll()
        .stream()
        .map(gameEntity -> new Game(
            gameEntity.getName(),
            gameEntity.getId()))
            .toList();
    }
    
    @Override 
    public Optional<Game> findById(Long id) {
        return jpaGameRepository.findById(id).map(gameEntity -> new Game(
            gameEntity.getName(),
            gameEntity.getId()));
    }

    @Override 
    public Game save(Game game) {
        if (game.id() == null) {
            GameEntity gameEntity = new GameEntity(game.name());
            GameEntity savedGameEntity = jpaGameRepository.save(gameEntity);
            return new Game(savedGameEntity.getName(), savedGameEntity.getId());
        }
        GameEntity existingEntity = jpaGameRepository.findById(game.id())
        .orElseThrow(() -> new NoSuchElementException("Game with ID " + game.id() + " not found"));
        existingEntity.setName(game.name());
        GameEntity updatedEntity = jpaGameRepository.save(existingEntity);
        return new Game(updatedEntity.getName(), updatedEntity.getId());
    }

    @Override 
    public void deleteById(Long id) {
        jpaGameRepository.deleteById(id);
    }

}
