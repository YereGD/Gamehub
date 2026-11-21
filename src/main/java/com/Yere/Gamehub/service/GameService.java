package com.Yere.Gamehub.service;

import com.Yere.Gamehub.exception.GameAlreadyExistsException;
import com.Yere.Gamehub.exception.GameNotFoundException;
import com.Yere.Gamehub.exception.InvalidGameException;
import com.Yere.Gamehub.model.Game;
import com.Yere.Gamehub.repository.GameRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service

public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(long id) {
        if (id <= 0) {
            throw new InvalidGameException("Invalid game ID: " + id + ". ID must be a positive number.");
        }
        return gameRepository.findById(id)
            .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
    }
    
    public Game createGame(Game game) {
        if(game.id() != null) {
            throw new InvalidGameException("New game should not have an ID");
        }
        if(gameRepository.existsByNameIgnoreCase(game.name())) {
            throw new GameAlreadyExistsException("Game with name '" + game.name() + "' already exists");
        }
        return gameRepository.save(game);
    }
    
    @Transactional 
    public Game updateGame(Game updatedGame) {
        validateId(updatedGame.id());
        if(gameRepository.existsByNameIgnoreCaseAndIdNot(updatedGame.name(), updatedGame.id())) {
            throw new GameAlreadyExistsException("Game with name '" + updatedGame.name() + "' already exists");
        }
        return gameRepository.update(updatedGame)
            .orElseThrow(() -> new GameNotFoundException("Game with ID " + updatedGame.id() + " not found"));
    }

    @Transactional
    public void deleteGame(Long id) {
        validateId(id);
        if(!gameRepository.deleteById(id)) {
            throw new GameNotFoundException("Game with ID " + id + " not found");
        }
    }


    private void validateId(Long id) {
        if (id <= 0 || id == null) {
            throw new InvalidGameException("Invalid game ID: " + id + ". ID must be a positive number.");
        }
    }
}