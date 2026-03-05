package com.hexagonal.server.infra.common.mock.account;

import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.infra.common.constant.account.Email;
import com.hexagonal.server.infra.common.constant.account.Name;
import com.hexagonal.server.infra.common.constant.account.Password;
import com.hexagonal.server.infra.common.constant.account.Username;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

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

    public static Account generateAccount(Money balance) {
        return new Account(
                Email.EMAIL_1,
                Username.USERNAME_1,
                Password.PASSWORD_1,
                Name.ACCOUNT_NAME_1,
                balance);
    }

}
