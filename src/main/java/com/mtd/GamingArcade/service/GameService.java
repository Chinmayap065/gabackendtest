package com.mtd.GamingArcade.service;

import com.mtd.GamingArcade.dto.CreateGameRequest;
import com.mtd.GamingArcade.entity.Game;
import com.mtd.GamingArcade.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List; // <-- Make sure this import is added

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(CreateGameRequest request) {
        Game game = new Game();
        game.setName(request.name());
        game.setPrice(request.price());
        game.setDescription(request.description());
        return gameRepository.save(game);
    }

    // --- ADD THIS NEW METHOD ---
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}