package com.hexagonal.server.identity.application.account.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hexagonal.server.identity.application.account.model.dto.AccountDto;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountResponse(AccountDto accountDto) {
}
