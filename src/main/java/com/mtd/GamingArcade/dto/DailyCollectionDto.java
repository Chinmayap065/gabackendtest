package com.mtd.GamingArcade.dto;

import java.time.LocalDateTime;

// Represents a single financial activity for the day
public record DailyCollectionDto(
    String type, // Will be "RECHARGE" or "GAME_PLAY"
    String memberName,
    Double amount,
    LocalDateTime dateTime
) {}