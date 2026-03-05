package com.hexagonal.server.account.application.model.request.transaction;

import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;

public record TransactionUpdateRequest(TransactionStatusEnum transactionStatusEnum) {
}
