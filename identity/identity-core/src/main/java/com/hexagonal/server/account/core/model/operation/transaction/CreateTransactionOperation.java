package com.hexagonal.server.account.core.model.operation.transaction;

import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionType;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public record CreateTransactionOperation(
        TransactionType type,
        Money amount,
        Description description,
        Id debtorAccountId,
        Id beneficiaryAccountId, TransactionStatusEnum status) {
}
