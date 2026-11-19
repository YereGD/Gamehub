package com.Yere.Gamehub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.Yere.Gamehub.mapper.GameEntityMapper;
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
        .map(GameEntityMapper::toGame)
            .toList();
    }
    
    @Override 
    public Optional<Game> findById(Long id) {
        return jpaGameRepository.findById(id).map(GameEntityMapper::toGame);
    }

    @Override 
    public Game save(Game game) {
        GameEntity gameEntity = GameEntityMapper.toEntity(game);
        GameEntity savedGameEntity = jpaGameRepository.save(gameEntity);
        return GameEntityMapper.toGame(savedGameEntity);
    }

    @Override
    public Optional<Game> update(Game game) {
        return jpaGameRepository.findById(game.id())
        .map(existingEntity -> {
            GameEntityMapper.updateEntity(existingEntity, game);
            return GameEntityMapper.toGame(existingEntity);
        });       
    }

    @Override 
    public boolean deleteById(Long id) {
        Optional<GameEntity> gameEntity = jpaGameRepository.findById(id);
        if(gameEntity.isEmpty()) {
            return false;
        }
        jpaGameRepository.delete(gameEntity.get());
        return true;
    }

}
