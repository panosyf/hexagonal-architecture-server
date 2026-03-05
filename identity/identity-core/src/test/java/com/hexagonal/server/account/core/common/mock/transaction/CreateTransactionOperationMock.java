package com.hexagonal.server.account.core.common.mock.transaction;

import com.hexagonal.server.account.core.common.constant.transaction.TransactionId;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionType;
import com.hexagonal.server.account.core.model.operation.transaction.CreateTransactionOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.math.BigDecimal;

public class CreateTransactionOperationMock {

    private CreateTransactionOperationMock() {
    }

    public static CreateTransactionOperation generateCreateTransactionOperation() {
        return new CreateTransactionOperation(
                TransactionType.TRANSFER,
                Money.of(BigDecimal.valueOf(5)),
                Description.emptyDescription(),
                TransactionId.ACCOUNT_ID_1,
                TransactionId.ACCOUNT_ID_2,
                TransactionStatusEnum.PENDING
        );
    }

}
