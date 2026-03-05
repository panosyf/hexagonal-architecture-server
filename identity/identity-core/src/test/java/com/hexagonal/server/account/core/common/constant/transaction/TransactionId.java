package com.hexagonal.server.account.core.common.constant.transaction;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public class TransactionId {

    private TransactionId() {
    }

    public static final Id ACCOUNT_ID_1 = com.hexagonal.server.shared.kernel.common.valueobjects.Id.valueOf("accountId1");
    public static final Id ACCOUNT_ID_2 = com.hexagonal.server.shared.kernel.common.valueobjects.Id.valueOf("accountId2");
    public static final Id TRANSACTION_ID_1 = com.hexagonal.server.shared.kernel.common.valueobjects.Id.valueOf("transactionId1");
    public static final Id TRANSACTION_ID_2 = com.hexagonal.server.shared.kernel.common.valueobjects.Id.valueOf("transactionId2");

}
