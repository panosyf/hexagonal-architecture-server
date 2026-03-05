package com.hexagonal.server.account.core.common.mock.account;


import com.hexagonal.server.account.core.common.constant.account.AccountId;
import com.hexagonal.server.account.core.model.operation.account.GetAccountOperation;

public class GetAccountOperationMock {

    private GetAccountOperationMock() {
    }

    public static GetAccountOperation generateGetAccountOperation() {
        return new GetAccountOperation(AccountId.ACCOUNT_ID_1);
    }

}
