package com.hexagonal.server.identity.infra.integration.test.common.mock.transaction;

import com.hexagonal.server.identity.application.model.request.transaction.TransactionUpdateRequest;
import com.hexagonal.server.identity.core.model.enums.transaction.TransactionStatusEnum;

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
