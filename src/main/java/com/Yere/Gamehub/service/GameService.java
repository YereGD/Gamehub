package com.Yere.Gamehub.service;

import com.Yere.Gamehub.model.Game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service

public class GameService {

    private final List<Game> games = new ArrayList<>(List.of(
		new Game("Valorant", 1),
		new Game("Minecraft", 2),
		new Game("League of Legends", 3)
    ));

    public List<Game> getGames() {
        return games;
    }

    public Game getGameById(int id) {
        return games.stream()
            .filter(game -> game.id() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }
    
    public Game createGame(Game game) {
        if (games.stream().anyMatch(existingGame -> existingGame.id() == game.id())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Game with this ID already exists");
        }
        games.add(game);
        return game;
    }

    public Game updateGame(int id, Game updatedGame) {
        if (updatedGame.id() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID in path and request body must match");
        }
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).id() == id) {
                games.set(i, updatedGame);
                return updatedGame;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
    }

    public void deleteGame(int id) {
        if (!games.removeIf(game -> game.id() == id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
    }
}