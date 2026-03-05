package com.hexagonal.server.account.application.common.mock.account;

import com.hexagonal.server.account.application.common.constant.account.AccountId;
import com.hexagonal.server.account.core.model.operation.account.DecreaseBalanceOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.math.BigDecimal;

public class DecreaseBalanceOperationMock {

    private DecreaseBalanceOperationMock() {
    }

    public static DecreaseBalanceOperation generateDecreaseBalanceOperation() {
        return new DecreaseBalanceOperation(AccountId.ACCOUNT_ID_1, Money.of(BigDecimal.TEN));
    }

}
