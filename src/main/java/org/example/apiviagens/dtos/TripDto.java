package org.example.apiviagens.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TripDto(
        @NotBlank String title,
        LocalDate startDate,
        LocalDate endDate,
        String imageUrl,
        @NotNull Long destinationId
) {
}
