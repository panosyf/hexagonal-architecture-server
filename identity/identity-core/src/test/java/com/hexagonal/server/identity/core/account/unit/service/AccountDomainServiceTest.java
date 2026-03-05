package com.hexagonal.server.identity.core.account.unit.service;

import com.hexagonal.server.identity.core.account.common.constant.*;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.exception.elementnotfound.AccountNotFoundException;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.model.operation.GetAccountOperation;
import com.hexagonal.server.identity.core.account.port.out.AccountRepositoryPort;
import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import com.hexagonal.server.identity.core.account.service.AccountDomainServiceImpl;
import com.hexagonal.server.shared.kernel.common.exception.constants.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.exception.utils.ErrorUtils;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static com.hexagonal.server.identity.core.account.common.mock.AccountMock.generateAccount;
import static com.hexagonal.server.identity.core.account.common.mock.CreateAccountOperationMock.generateCreateAccountOperation;
import static com.hexagonal.server.identity.core.account.common.mock.GetAccountOperationMock.generateGetAccountOperation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AccountDomainServiceTest {

    private final AccountRepositoryPort accountRepositoryPort = mock(AccountRepositoryPort.class);
    private AccountDomainService accountDomainService;
    private final ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);

    @BeforeEach
    void init() {
        accountDomainService = new AccountDomainServiceImpl(accountRepositoryPort);
    }

    @Test
    void getAccountTest() {
        // given
        GetAccountOperation getAccountOperation = generateGetAccountOperation();
        Account account = generateAccount();
        given(accountRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willReturn(account);
        // when
        Account accountResult = accountDomainService.getAccount(getAccountOperation);
        // then
        assertAll(
                () -> assertEquals(Email.EMAIL_1, accountResult.getEmail()),
                () -> assertEquals(Username.USERNAME_1, accountResult.getUsername()),
                () -> assertEquals(Password.PASSWORD_1, accountResult.getPassword()),
                () -> assertEquals(Name.ACCOUNT_NAME_1, accountResult.getName()),
                () -> assertEquals(account.getCreatedAt(), accountResult.getCreatedAt()),
                () -> assertEquals(account.getUpdatedAt(), accountResult.getUpdatedAt())
        );
    }

    @Test
    void getAccountThrowsAccountNotFoundExceptionTest() {
        // given
        GetAccountOperation getAccountOperation = generateGetAccountOperation();
        given(accountRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willThrow(new AccountNotFoundException(AccountId.ACCOUNT_ID_1.getValue()));
        // then
        assertThatThrownBy(() -> accountDomainService.getAccount(getAccountOperation))
                .isInstanceOf(AccountNotFoundException.class)
                .hasMessage(ErrorUtils.generateErrorMessage(ErrorMessageConstants.ACCOUNT_NOT_FOUND_EXCEPTION, AccountId.ACCOUNT_ID_1.getValue()));
    }

    @Test
    void createAccountTest() {
        // given
        CreateAccountOperation createAccountOperation = generateCreateAccountOperation();
        Timestamp timestampBeforeAccountCreation = Timestamp.now().minusNanos(100);
        Account account = generateAccount();
        given(accountRepositoryPort.save(any(Account.class)))
                .willReturn(account);
        // when
        accountDomainService.createAccount(createAccountOperation);
        // then
        verify(accountRepositoryPort, times(1))
                .save(accountCaptor.capture());
        Account persistedAccount = accountCaptor.getValue();
        assertAll(
                () -> assertEquals(Email.EMAIL_1, persistedAccount.getEmail()),
                () -> assertEquals(Username.USERNAME_1, persistedAccount.getUsername()),
                () -> assertEquals(Password.PASSWORD_1, persistedAccount.getPassword()),
                () -> assertEquals(Name.ACCOUNT_NAME_1, persistedAccount.getName()),
                () -> assertThat(timestampBeforeAccountCreation.isBefore(persistedAccount.getCreatedAt())).isTrue(),
                () -> assertThat(timestampBeforeAccountCreation.isBefore(persistedAccount.getUpdatedAt())).isTrue()
        );
    }

}
