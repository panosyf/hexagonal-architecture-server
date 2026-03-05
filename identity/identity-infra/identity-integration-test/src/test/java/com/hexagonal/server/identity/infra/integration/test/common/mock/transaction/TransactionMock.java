package com.hexagonal.server.identity.infra.integration.test.common.mock.transaction;

import com.hexagonal.server.identity.core.domain.transaction.Transaction;
import com.hexagonal.server.identity.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.identity.core.model.enums.transaction.TransactionType;
import com.hexagonal.server.identity.infra.integration.test.common.constant.transaction.TransactionId;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.math.BigDecimal;

public class TransactionMock {

    private TransactionMock() {
    }

    public static Transaction generateTransaction() {
        return new Transaction(
                TransactionType.TRANSFER,
                Money.of(BigDecimal.valueOf(5)),
                Description.emptyDescription(),
                TransactionId.ACCOUNT_ID_1,
                TransactionId.ACCOUNT_ID_2,
                TransactionStatusEnum.PENDING);
    }

    public static Transaction generateTransaction(com.hexagonal.server.shared.kernel.common.valueobjects.Id id) {
        return new Transaction(
                id,
                TransactionType.TRANSFER,
                Money.of(BigDecimal.valueOf(5)),
                Description.emptyDescription(),
                TransactionId.ACCOUNT_ID_1,
                TransactionId.ACCOUNT_ID_2,
                TransactionStatusEnum.PENDING);
    }

    public static Transaction generatePendingTransaction(com.hexagonal.server.shared.kernel.common.valueobjects.Id id) {
        Transaction transaction = generateTransaction(id);
        transaction.updateStatus(TransactionStatusEnum.PENDING);
        return transaction;
    }

}
