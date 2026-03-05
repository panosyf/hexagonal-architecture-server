package com.hexagonal.server.identity.application.application.model.request.transaction;

import com.hexagonal.server.identity.core.core.model.enums.transaction.TransactionStatusEnum;

public record TransactionUpdateRequest(TransactionStatusEnum transactionStatusEnum) {
}
