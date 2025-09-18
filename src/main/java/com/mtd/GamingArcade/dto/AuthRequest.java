package com.mtd.GamingArcade.dto;

// Using a 'record' is a modern way to create a simple data carrier class.
public record AuthRequest(String username, String password) {}