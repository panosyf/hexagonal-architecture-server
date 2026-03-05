package com.hexagonal.server.infra.common.mock.account;

import com.hexagonal.server.account.application.model.request.account.AccountCreateRequest;
import com.hexagonal.server.infra.common.constant.account.Email;
import com.hexagonal.server.infra.common.constant.account.Name;
import com.hexagonal.server.infra.common.constant.account.Password;
import com.hexagonal.server.infra.common.constant.account.Username;

public class AccountCreateRequestMock {

    private AccountCreateRequestMock() {
    }

    public static AccountCreateRequest generateAccountCreateRequest() {
        return new AccountCreateRequest(
                Email.EMAIL_1.getValue(),
                Username.USERNAME_1.getValue(),
                Password.PASSWORD_1.getValue(),
                Name.ACCOUNT_NAME_1.getFirstName(),
                Name.ACCOUNT_NAME_1.getLastName());
    }

}
