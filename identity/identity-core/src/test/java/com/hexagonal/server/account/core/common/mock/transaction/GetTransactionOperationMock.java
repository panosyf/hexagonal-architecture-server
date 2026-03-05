package com.hexagonal.server.account.core.common.mock.transaction;

import com.hexagonal.server.account.core.common.constant.transaction.TransactionId;
import com.hexagonal.server.account.core.model.operation.transaction.GetTransactionOperation;

public class GetTransactionOperationMock {

    private GetTransactionOperationMock() {
    }

    public static GetTransactionOperation generateGetTransactionOperation() {
        return new GetTransactionOperation(TransactionId.TRANSACTION_ID_1);
    }

}
