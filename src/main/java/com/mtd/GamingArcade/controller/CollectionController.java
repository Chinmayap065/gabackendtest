package com.mtd.GamingArcade.controller;

import com.mtd.GamingArcade.dto.CollectionResponseDto;
import com.mtd.GamingArcade.service.CollectionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/{date}")
    public ResponseEntity<CollectionResponseDto> getCollectionByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        CollectionResponseDto response = collectionService.getDailyCollection(date);
        return ResponseEntity.ok(response);
    }
}