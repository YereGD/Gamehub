package com.Yere.Gamehub.service;

import com.Yere.Gamehub.model.Game;
import com.Yere.Gamehub.repository.GameRepository;

import java.util.ArrayList;
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

    public Game getGameById(int id) {
        return gameRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }
    
    public Game createGame(Game game) {
        if (!gameRepository.findById(game.id()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Game with this ID already exists");
        }
        gameRepository.save(game);
        return game;
    }

    public Game updateGame(int id, Game updatedGame) {
        if (updatedGame.id() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID in path and request body must match");
        }
        if (gameRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return gameRepository.save(updatedGame);
    }

    public void deleteGame(int id) {
        if (gameRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        gameRepository.deleteById(id);
    }
}