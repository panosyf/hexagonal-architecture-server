package com.hexagonal.server.identity.application.model.request.transaction;

import com.hexagonal.server.identity.core.model.enums.transaction.TransactionStatusEnum;

public record TransactionUpdateRequest(TransactionStatusEnum transactionStatusEnum) {
}
