package com.Yere.Gamehub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.Yere.Gamehub.service.GameService;

import jakarta.validation.Valid;

import com.Yere.Gamehub.model.Game;


import java.util.List;

@RestController

public class GameController {

	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/api/games")
	public List<Game> getGames() {
        return gameService.getGames();
	}

	@GetMapping ("/api/games/{id}")
	public Game getGameById(@PathVariable int id) {
		return gameService.getGameById(id);
	}

	@PostMapping (path = "/api/games", consumes = "application/json")
	public ResponseEntity<Game> createGame(@Valid @RequestBody Game game) {
		Game gameCreated = gameService.createGame(game);
		return new ResponseEntity<>(gameCreated, HttpStatus.CREATED);
	}
	
	@PutMapping (path = "/api/games/{id}", consumes = "application/json")
	public ResponseEntity<Game> updateGame(@PathVariable int id, @Valid @RequestBody Game updatedGame) {
		Game gameUpdated = gameService.updateGame(id, updatedGame);
		return new ResponseEntity<>(gameUpdated, HttpStatus.OK);
	}

	@DeleteMapping ("/api/games/{id}")
	public ResponseEntity<Void> deleteGame(@PathVariable int id) {
		gameService.deleteGame(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

