package com.mtd.GamingArcade;

import com.mtd.GamingArcade.controller.GameController;
import com.mtd.GamingArcade.entity.Game;
import com.mtd.GamingArcade.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class) // 1. We specify that we are testing the GameController
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc; // 2. This lets us simulate HTTP requests

    @MockBean
    private GameService gameService; // 3. We create a "mock" of the service so we don't need a real database

    @Test
    void shouldReturnAllGames() throws Exception {
        // 4. Arrange: We define what the mock service should return
        Game chess = new Game();
        chess.setId("1");
        chess.setName("Chess");
        chess.setPrice(50.0);
        chess.setDescription("A classic board game");

        when(gameService.getAllGames()).thenReturn(List.of(chess));

        // 5. Act & Assert: We perform the API call and check the results
        mockMvc.perform(get("/api/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Chess"))
                .andExpect(jsonPath("$[0].price").value(50.0));
    }
}