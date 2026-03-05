package com.hexagonal.server.identity.application.common.mock.account;


import com.hexagonal.server.identity.application.common.constant.account.AccountId;
import com.hexagonal.server.identity.core.model.operation.account.GetAccountOperation;

public class GetAccountOperationMock {

    private GetAccountOperationMock() {
    }

    public static GetAccountOperation generateGetAccountOperation() {
        return new GetAccountOperation(AccountId.ACCOUNT_ID_1);
    }

}
