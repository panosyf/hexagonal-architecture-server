package com.hexagonal.server.identity.common.mock;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.common.constant.Email;
import com.hexagonal.server.identity.common.constant.Name;
import com.hexagonal.server.identity.common.constant.Password;
import com.hexagonal.server.identity.common.constant.Username;

public class AccountMock {

    private AccountMock() {
    }

    public static Account generateAccount() {
        return new Account(
                Email.EMAIL_1,
                Username.USERNAME_1,
                Password.PASSWORD_1,
                Name.ACCOUNT_NAME_1);
    }

}
