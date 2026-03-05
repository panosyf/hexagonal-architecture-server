package com.hexagonal.server.account.core.model.operation.transaction;

import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public record UpdateTransactionOperation(Id id, TransactionStatusEnum transactionStatusEnum) {
}
