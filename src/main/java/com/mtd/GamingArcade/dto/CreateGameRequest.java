package com.mtd.GamingArcade.dto;

/**
 * This record represents the data needed to create a new game.
 * It's a modern, concise way to create a simple data-holding class in Java.
 */
public record CreateGameRequest(String name, double price, String description) {}