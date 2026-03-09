package com.hexagonal.server.identity.application.account.shared.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.Instant;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountDto(
        String id,
        String firstname,
        String lastname,
        Instant createdAt,
        Instant updatedAt) {
}