package com.hexagonal.server.identity.application.account.common.mock;

import com.hexagonal.server.identity.application.account.common.constant.Email;
import com.hexagonal.server.identity.application.account.common.constant.Name;
import com.hexagonal.server.identity.application.account.common.constant.Password;
import com.hexagonal.server.identity.application.account.common.constant.Username;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.CreateAccountRequest;


public class AccountCreateRequestMock {

    private AccountCreateRequestMock() {
    }

    public static CreateAccountRequest generateAccountCreateRequest() {
        return new CreateAccountRequest(
                Email.EMAIL_1.getValue(),
                Username.USERNAME_1.getValue(),
                Password.PASSWORD_1.getValue(),
                Name.ACCOUNT_NAME_1.getFirstName(),
                Name.ACCOUNT_NAME_1.getLastName());
    }

}
