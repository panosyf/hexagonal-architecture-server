package com.hexagonal.server.identity.infra.integration.test.common.mock;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.infra.integration.test.common.constant.Email;
import com.hexagonal.server.identity.infra.integration.test.common.constant.Name;
import com.hexagonal.server.identity.infra.integration.test.common.constant.Password;
import com.hexagonal.server.identity.infra.integration.test.common.constant.Username;
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

}
