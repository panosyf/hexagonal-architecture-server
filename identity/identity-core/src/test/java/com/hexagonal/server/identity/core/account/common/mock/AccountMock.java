package com.hexagonal.server.identity.core.account.common.mock;

import com.hexagonal.server.identity.core.account.common.constant.Email;
import com.hexagonal.server.identity.core.account.common.constant.Name;
import com.hexagonal.server.identity.core.account.common.constant.Password;
import com.hexagonal.server.identity.core.account.common.constant.Username;
import com.hexagonal.server.identity.core.account.domain.Account;

public class AccountMock {

    private AccountMock() {
    }

    public static Account generateAccount() {
        return Account.create(
                Email.EMAIL_1,
                Username.USERNAME_1,
                Password.PASSWORD_1,
                Name.ACCOUNT_NAME_1);
    }

}
