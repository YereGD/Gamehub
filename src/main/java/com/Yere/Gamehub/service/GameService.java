package com.Yere.Gamehub.service;

import com.Yere.Gamehub.model.Game;
import com.Yere.Gamehub.repository.GameRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be a positive integer");
        }
        return gameRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }
    
    public Game createGame(Game game) {
        if(game.id() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New game should not have an ID");
        }
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        if (gameRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return gameRepository.save(updatedGame);
    }

    public void deleteGame(long id) {
        if (gameRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        gameRepository.deleteById(id);
    }
}