package com.hexagonal.server.identity.core.core.common.mock.account;


import com.hexagonal.server.identity.core.core.common.constant.account.AccountId;
import com.hexagonal.server.identity.core.core.model.operation.account.GetAccountOperation;

public class GetAccountOperationMock {

    private GetAccountOperationMock() {
    }

    public static GetAccountOperation generateGetAccountOperation() {
        return new GetAccountOperation(AccountId.ACCOUNT_ID_1);
    }

}
