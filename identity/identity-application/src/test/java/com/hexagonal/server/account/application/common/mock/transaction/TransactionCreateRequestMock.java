package com.hexagonal.server.account.application.common.mock.transaction;

import com.hexagonal.server.account.application.common.constant.transaction.TransactionId;
import com.hexagonal.server.account.application.model.request.transaction.TransactionCreateRequest;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionType;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.math.BigDecimal;

public class TransactionCreateRequestMock {

    private TransactionCreateRequestMock() {
    }

    public static TransactionCreateRequest generateTransactionCreateRequest() {
        return new TransactionCreateRequest(
                TransactionType.TRANSFER,
                Money.of(BigDecimal.valueOf(5)).getValue(),
                Description.emptyDescription().getValue(),
                TransactionId.ACCOUNT_ID_1.getValue(),
                TransactionId.ACCOUNT_ID_2.getValue());
    }

    public static TransactionCreateRequest generateTransactionCreateRequest(String debtorAccountId, String beneficiaryAccountId) {
        return new TransactionCreateRequest(
                TransactionType.TRANSFER,
                Money.of(BigDecimal.valueOf(5)).getValue(),
                Description.emptyDescription().getValue(),
                debtorAccountId,
                beneficiaryAccountId);
    }

}
