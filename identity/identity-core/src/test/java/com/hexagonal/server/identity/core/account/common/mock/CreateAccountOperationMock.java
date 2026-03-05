package com.hexagonal.server.identity.core.account.common.mock;

import com.hexagonal.server.identity.core.account.common.constant.Email;
import com.hexagonal.server.identity.core.account.common.constant.Name;
import com.hexagonal.server.identity.core.account.common.constant.Password;
import com.hexagonal.server.identity.core.account.common.constant.Username;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;

public class CreateAccountOperationMock {

    private CreateAccountOperationMock() {
    }

    public static CreateAccountOperation generateCreateAccountOperation() {
        return new CreateAccountOperation(
                Email.EMAIL_1,
                Username.USERNAME_1,
                Password.PASSWORD_1,
                Name.ACCOUNT_NAME_1);
    }

}
