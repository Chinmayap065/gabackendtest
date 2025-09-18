package com.mtd.GamingArcade.dto;

import java.time.LocalDate;
import java.util.List;

// Represents the final response for the collection API
public record CollectionResponseDto(
    LocalDate date,
    Double totalCollection,
    List<DailyCollectionDto> activities
) {}