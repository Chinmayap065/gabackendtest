package com.mtd.GamingArcade.controller;

import com.mtd.GamingArcade.dto.PlayGameRequest;
import com.mtd.GamingArcade.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/play")
    public ResponseEntity<String> playGame(@RequestBody PlayGameRequest request) {
        try {
            String result = transactionService.playGame(request.member_id(), request.game_id());
            if (result.contains("Insufficient")) {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}