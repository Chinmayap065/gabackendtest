package com.mtd.GamingArcade.controller;

import com.mtd.GamingArcade.dto.CreateGameRequest;
import com.mtd.GamingArcade.entity.Game;
import com.mtd.GamingArcade.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody CreateGameRequest request) {
        Game newGame = gameService.createGame(request);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }
}