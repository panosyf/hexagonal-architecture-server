package com.hexagonal.server.identity.core.core.common.mock.account;

import com.hexagonal.server.identity.core.core.common.constant.account.AccountId;
import com.hexagonal.server.identity.core.core.model.operation.account.DecreaseBalanceOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.math.BigDecimal;

public class DecreaseBalanceOperationMock {

    private DecreaseBalanceOperationMock() {
    }

    public static DecreaseBalanceOperation generateDecreaseBalanceOperation() {
        return new DecreaseBalanceOperation(AccountId.ACCOUNT_ID_1, Money.of(BigDecimal.TEN));
    }

}
