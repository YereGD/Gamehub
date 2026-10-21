package com.Yere.Gamehub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Yere.Gamehub.model.Game;

import java.util.List;

@RestController

public class GameController {

	List<Game> games = List.of(
		new Game("Valorant", 1),
		new Game("Minecraft", 2),
		new Game("League of Legends", 3)
	);

	@GetMapping("/api/games")
	public List<Game> getGames() {
        return games;
	}

	@GetMapping ("/api/games/{id}")
	@ResponseBody 
	public Game getGameById(@PathVariable int id) {
		return games.stream()
			.filter(game -> game.id() == id)
			.findFirst()
			.orElse(new Game("Missing ID", id)); // Se devuelve un juego de ejemplo con el ID proporcionado. En una implementación real, se buscaría en una base de datos o en una lista de juegos.
	}
}

