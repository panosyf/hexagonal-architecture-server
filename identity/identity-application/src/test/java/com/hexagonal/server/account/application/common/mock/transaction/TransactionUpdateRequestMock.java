package com.hexagonal.server.account.application.common.mock.transaction;

import com.hexagonal.server.account.application.model.request.transaction.TransactionUpdateRequest;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;

public class TransactionUpdateRequestMock {

    private TransactionUpdateRequestMock() {
    }

    public static TransactionUpdateRequest generateTransactionUpdateRequest() {
        return new TransactionUpdateRequest(TransactionStatusEnum.COMPLETED);
    }

    public static TransactionUpdateRequest generateTransactionUpdateRequest(TransactionStatusEnum transactionStatusEnum) {
        return new TransactionUpdateRequest(transactionStatusEnum);
    }

}
