package com.hexagonal.server.identity.core.account.service;

import com.hexagonal.server.identity.core.account.common.constant.Email;
import com.hexagonal.server.identity.core.account.common.constant.Name;
import com.hexagonal.server.identity.core.account.common.constant.Password;
import com.hexagonal.server.identity.core.account.common.constant.Username;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.CreateAccountOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static com.hexagonal.server.identity.core.account.common.mock.CreateAccountOperationMock.generateCreateAccountOperation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountDomainServiceTest {

    private AccountDomainService accountDomainService;
    private final ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);

    @BeforeEach
    void init() {
        accountDomainService = new AccountDomainServiceImpl();
    }

    @Test
    void createAccountTest() {
        // given
        CreateAccountOperation createAccountOperation = generateCreateAccountOperation();
        Timestamp timestampBeforeAccountCreation = Timestamp.now().minusNanos(100);
        // when
        Account account = accountDomainService.createAccount(createAccountOperation);
        // then
        assertAll(
                () -> assertEquals(Email.EMAIL_1, account.getEmail()),
                () -> assertEquals(Username.USERNAME_1, account.getUsername()),
                () -> assertEquals(Password.PASSWORD_1, account.getPassword()),
                () -> assertEquals(Name.ACCOUNT_NAME_1, account.getName()),
                () -> assertThat(timestampBeforeAccountCreation.isBefore(account.getCreatedAt())).isTrue(),
                () -> assertThat(timestampBeforeAccountCreation.isBefore(account.getUpdatedAt())).isTrue()
        );
    }

}
