package com.hexagonal.server.identity.core.account.common.mock;


import com.hexagonal.server.identity.core.account.common.constant.AccountId;
import com.hexagonal.server.identity.core.account.model.operation.GetAccountOperation;

public class GetAccountOperationMock {

    private GetAccountOperationMock() {
    }

    public static GetAccountOperation generateGetAccountOperation() {
        return new GetAccountOperation(AccountId.ACCOUNT_ID_1);
    }

}
