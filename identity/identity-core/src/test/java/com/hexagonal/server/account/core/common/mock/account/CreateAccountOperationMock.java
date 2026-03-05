package com.hexagonal.server.account.core.common.mock.account;

import com.hexagonal.server.account.core.common.constant.account.Email;
import com.hexagonal.server.account.core.common.constant.account.Name;
import com.hexagonal.server.account.core.common.constant.account.Password;
import com.hexagonal.server.account.core.common.constant.account.Username;
import com.hexagonal.server.account.core.model.operation.account.CreateAccountOperation;

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
