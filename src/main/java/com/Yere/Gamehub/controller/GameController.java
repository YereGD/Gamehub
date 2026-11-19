package com.Yere.Gamehub.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Yere.Gamehub.dto.CreateGameRequest;
import com.Yere.Gamehub.dto.GameResponse;
import com.Yere.Gamehub.dto.UpdatedGameRequest;
import com.Yere.Gamehub.mapper.GameMapper;
import com.Yere.Gamehub.model.Game;
import com.Yere.Gamehub.service.GameService;

import jakarta.validation.Valid;

@RestController

public class GameController {

	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/api/games")
	public ResponseEntity<List<GameResponse>> getGames() {
        List<GameResponse> gamesResponse = gameService.getGames().stream()
            .map(GameMapper::toResponse)
            .toList();
		return ResponseEntity.ok(gamesResponse);
	}

	@GetMapping ("/api/games/{id}")
	public ResponseEntity<GameResponse> getGameById(@PathVariable Long id) {
		Game game = gameService.getGameById(id);
		GameResponse response = GameMapper.toResponse(game);
		return ResponseEntity.ok(response);
	}

	@PostMapping (path = "/api/games", consumes = "application/json")
	public ResponseEntity<GameResponse> createGame(@Valid @RequestBody CreateGameRequest request) {
		Game game = GameMapper.toGame(request);
		Game gameCreated = gameService.createGame(game);
		GameResponse response = GameMapper.toResponse(gameCreated);	
		URI location = URI.create(String.format("/api/games/%s", gameCreated.id()));
		return ResponseEntity.created(location).body(response);
	}
	
	@PutMapping (path = "/api/games/{id}", consumes = "application/json")
	public ResponseEntity<GameResponse> updateGame(@PathVariable Long id, @Valid @RequestBody UpdatedGameRequest request) {
		Game game = GameMapper.toGame(request, id);
		Game gameUpdated = gameService.updateGame(game);
		GameResponse response = GameMapper.toResponse(gameUpdated);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping ("/api/games/{id}")
	public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
		gameService.deleteGame(id);
		return ResponseEntity.noContent().build();	
	}
}

