package com.hexagonal.server.identity.application.model.response.transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hexagonal.server.identity.core.model.enums.transaction.TransactionStatusEnum;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TransactionUpdateResponse(String id, TransactionStatusEnum status) {
}
