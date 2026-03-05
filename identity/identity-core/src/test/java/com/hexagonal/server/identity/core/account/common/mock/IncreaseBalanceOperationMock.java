package com.hexagonal.server.identity.core.account.common.mock;

import com.hexagonal.server.identity.core.account.common.constant.AccountId;
import com.hexagonal.server.identity.core.account.model.operation.IncreaseBalanceOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.math.BigDecimal;

public class IncreaseBalanceOperationMock {

    private IncreaseBalanceOperationMock() {
    }

    public static IncreaseBalanceOperation generateIncreaseBalanceOperation() {
        return new IncreaseBalanceOperation(AccountId.ACCOUNT_ID_1, Money.of(BigDecimal.TEN));
    }

}
