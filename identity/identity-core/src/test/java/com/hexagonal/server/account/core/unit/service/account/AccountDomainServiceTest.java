package com.hexagonal.server.account.core.unit.service.account;

import com.hexagonal.server.account.core.common.constant.account.*;
import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.account.core.exception.elementnotfound.account.AccountNotFoundException;
import com.hexagonal.server.account.core.model.operation.account.CreateAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.DecreaseBalanceOperation;
import com.hexagonal.server.account.core.model.operation.account.GetAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.IncreaseBalanceOperation;
import com.hexagonal.server.account.core.port.out.account.AccountRepositoryPort;
import com.hexagonal.server.account.core.service.account.AccountDomainService;
import com.hexagonal.server.account.core.service.account.AccountDomainServiceImpl;
import com.hexagonal.server.shared.kernel.common.exception.constants.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.exception.utils.ErrorUtils;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static com.hexagonal.server.account.core.common.mock.account.AccountMock.generateAccount;
import static com.hexagonal.server.account.core.common.mock.account.CreateAccountOperationMock.generateCreateAccountOperation;
import static com.hexagonal.server.account.core.common.mock.account.DecreaseBalanceOperationMock.generateDecreaseBalanceOperation;
import static com.hexagonal.server.account.core.common.mock.account.GetAccountOperationMock.generateGetAccountOperation;
import static com.hexagonal.server.account.core.common.mock.account.IncreaseBalanceOperationMock.generateIncreaseBalanceOperation;
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
    private static final Money BALANCE_0 = Money.zero();
    private static final Money BALANCE_5 = Money.of(BigDecimal.valueOf(5));
    private static final Money BALANCE_10 = Money.of(BigDecimal.TEN);
    private static final Money BALANCE_15 = Money.of(BigDecimal.valueOf(15));

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
                () -> assertEquals(BALANCE_0, accountResult.getBalance()),
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
                () -> assertEquals(BALANCE_0, persistedAccount.getBalance()),
                () -> assertThat(timestampBeforeAccountCreation.isBefore(persistedAccount.getCreatedAt())).isTrue(),
                () -> assertThat(timestampBeforeAccountCreation.isBefore(persistedAccount.getUpdatedAt())).isTrue()
        );
    }

    @Test
    void increaseBalanceTest() {
        // given
        IncreaseBalanceOperation increaseBalanceOperation = generateIncreaseBalanceOperation();
        Account account = generateAccount();
        given(accountRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willReturn(account);
        // when
        accountDomainService.increaseBalance(increaseBalanceOperation);
        // then
        verify(accountRepositoryPort, times(1))
                .updateBalance(accountCaptor.capture());
        assertThat(accountCaptor.getValue().getBalance()).isEqualTo(BALANCE_10);
    }

    @Test
    void increaseBalanceThrowsAccountNotFoundExceptionTest() {
        // given
        IncreaseBalanceOperation increaseBalanceOperation = generateIncreaseBalanceOperation();
        given(accountRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willThrow(new AccountNotFoundException(AccountId.ACCOUNT_ID_1.getValue()));
        // then
        assertThatThrownBy(() -> accountDomainService.increaseBalance(increaseBalanceOperation))
                .isInstanceOf(AccountNotFoundException.class)
                .hasMessage(ErrorUtils.generateErrorMessage(ErrorMessageConstants.ACCOUNT_NOT_FOUND_EXCEPTION, AccountId.ACCOUNT_ID_1.getValue()));
    }

    @Test
    void decreaseBalanceTest() {
        // given
        DecreaseBalanceOperation decreaseBalanceOperation = generateDecreaseBalanceOperation();
        Account account = generateAccount(BALANCE_15);
        given(accountRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willReturn(account);
        // when
        accountDomainService.decreaseBalance(decreaseBalanceOperation);
        // then
        verify(accountRepositoryPort, times(1))
                .updateBalance(accountCaptor.capture());
        assertThat(accountCaptor.getValue().getBalance()).isEqualTo(BALANCE_5);
    }

    @Test
    void decreaseBalanceTestThrowsAccountNotFoundExceptionTest() {
        // given
        DecreaseBalanceOperation decreaseBalanceOperation = generateDecreaseBalanceOperation();
        given(accountRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willThrow(new AccountNotFoundException(AccountId.ACCOUNT_ID_1.getValue()));
        // then
        assertThatThrownBy(() -> accountDomainService.decreaseBalance(decreaseBalanceOperation))
                .isInstanceOf(AccountNotFoundException.class)
                .hasMessage(ErrorUtils.generateErrorMessage(ErrorMessageConstants.ACCOUNT_NOT_FOUND_EXCEPTION, AccountId.ACCOUNT_ID_1.getValue()));
    }

}
