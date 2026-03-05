package com.hexagonal.server.identity.application.model.response.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hexagonal.server.identity.application.model.dto.account.AccountDto;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountResponse(AccountDto accountDto) {
}
