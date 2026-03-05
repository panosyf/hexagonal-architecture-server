package com.hexagonal.server.account.application.model.dto.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.Instant;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountDto(
        String id,
        String firstname,
        String lastname,
        BigDecimal balance,
        Instant createdAt,
        Instant updatedAt) {
}