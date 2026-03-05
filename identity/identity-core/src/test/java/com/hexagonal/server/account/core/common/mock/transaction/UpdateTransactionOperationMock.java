package com.hexagonal.server.account.core.common.mock.transaction;

import com.hexagonal.server.account.core.common.constant.transaction.TransactionId;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.model.operation.transaction.UpdateTransactionOperation;

public class UpdateTransactionOperationMock {

    private UpdateTransactionOperationMock() {
    }

    public static UpdateTransactionOperation generateUpdateTransactionOperation() {
        return new UpdateTransactionOperation(TransactionId.TRANSACTION_ID_1, TransactionStatusEnum.COMPLETED);
    }

}

