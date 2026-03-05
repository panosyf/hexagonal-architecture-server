package com.hexagonal.server.account.core.common.mock.account;

import com.hexagonal.server.account.core.common.constant.account.AccountId;
import com.hexagonal.server.account.core.model.operation.account.IncreaseBalanceOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.math.BigDecimal;

public class IncreaseBalanceOperationMock {

    private IncreaseBalanceOperationMock() {
    }

    public static IncreaseBalanceOperation generateIncreaseBalanceOperation() {
        return new IncreaseBalanceOperation(AccountId.ACCOUNT_ID_1, Money.of(BigDecimal.TEN));
    }

}
