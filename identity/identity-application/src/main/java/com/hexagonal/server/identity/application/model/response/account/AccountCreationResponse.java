package com.hexagonal.server.identity.application.model.response.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hexagonal.server.identity.core.model.enums.account.AccountCreationStatusEnum;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountCreationResponse(String id, AccountCreationStatusEnum status) {
}
