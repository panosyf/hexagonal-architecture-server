package com.hexagonal.server.identity.application.account.common.mock;

import com.hexagonal.server.identity.application.account.common.constant.AccountId;
import com.hexagonal.server.identity.core.account.model.operation.DecreaseBalanceOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.math.BigDecimal;

public class DecreaseBalanceOperationMock {

    private DecreaseBalanceOperationMock() {
    }

    public static DecreaseBalanceOperation generateDecreaseBalanceOperation() {
        return new DecreaseBalanceOperation(AccountId.ACCOUNT_ID_1, Money.of(BigDecimal.TEN));
    }

}
