package com.hexagonal.server.account.application.unit.usecase.account;

import com.hexagonal.server.account.application.usecase.account.AccountUsecase;
import com.hexagonal.server.account.application.usecase.account.AccountUsecaseImpl;
import com.hexagonal.server.account.application.common.constant.account.Email;
import com.hexagonal.server.account.application.common.constant.account.Name;
import com.hexagonal.server.account.application.common.constant.account.Password;
import com.hexagonal.server.account.application.common.constant.account.Username;
import com.hexagonal.server.account.application.common.constant.transaction.TransactionId;
import com.hexagonal.server.account.application.converter.account.in.AccountCreateRequestToOperation;
import com.hexagonal.server.account.application.converter.account.out.AccountToDto;
import com.hexagonal.server.account.application.model.dto.account.AccountDto;
import com.hexagonal.server.account.application.model.request.account.AccountCreateRequest;
import com.hexagonal.server.account.application.model.response.account.AccountCreationResponse;
import com.hexagonal.server.account.application.model.response.account.AccountResponse;
import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.account.core.model.enums.account.AccountCreationStatusEnum;
import com.hexagonal.server.account.core.service.account.AccountDomainService;
import com.hexagonal.server.account.core.model.operation.account.CreateAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.DecreaseBalanceOperation;
import com.hexagonal.server.account.core.model.operation.account.GetAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.IncreaseBalanceOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.core.convert.support.GenericConversionService;

import java.math.BigDecimal;

import static com.hexagonal.server.account.application.common.mock.account.AccountCreateRequestMock.generateAccountCreateRequest;
import static com.hexagonal.server.account.application.common.mock.account.AccountMock.generateAccount;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AccountUsecaseTest {

    private final AccountDomainService accountDomainService = mock(AccountDomainService.class);
    private final GenericConversionService genericConversionService = new GenericConversionService();
    private AccountUsecase accountUsecase;
    private final ArgumentCaptor<CreateAccountOperation> createAccountOperationCaptor = ArgumentCaptor.forClass(CreateAccountOperation.class);
    private final ArgumentCaptor<GetAccountOperation> getAccountOperationCaptor = ArgumentCaptor.forClass(GetAccountOperation.class);
    private final ArgumentCaptor<IncreaseBalanceOperation> increaseBalanceOperationCaptor = ArgumentCaptor.forClass(IncreaseBalanceOperation.class);
    private final ArgumentCaptor<DecreaseBalanceOperation> decreaseBalanceOperationCaptor = ArgumentCaptor.forClass(DecreaseBalanceOperation.class);

    @BeforeEach
    void init() {
        genericConversionService.addConverter(new AccountToDto());
        genericConversionService.addConverter(new AccountCreateRequestToOperation());
        accountUsecase = new AccountUsecaseImpl(accountDomainService, genericConversionService);
    }

    @Test
    void createAccountTest() {
        // given
        AccountCreateRequest accountCreateRequest = generateAccountCreateRequest();
        Account account = generateAccount();
        given(accountDomainService.createAccount(any(CreateAccountOperation.class)))
                .willReturn(account);
        // when
        AccountCreationResponse accountCreationResponse = accountUsecase.createAccount(accountCreateRequest);
        // then
        verify(accountDomainService, times(1))
                .createAccount(createAccountOperationCaptor.capture());
        CreateAccountOperation createAccountOperation = createAccountOperationCaptor.getValue();
        assertAll(
                () -> assertEquals(Email.EMAIL_1, createAccountOperation.email()),
                () -> assertEquals(Username.USERNAME_1, createAccountOperation.username()),
                () -> assertEquals(Password.PASSWORD_1, createAccountOperation.password()),
                () -> assertEquals(Name.ACCOUNT_NAME_1, createAccountOperation.name()),
                () -> assertEquals(AccountCreationStatusEnum.SUCCESSFUL, accountCreationResponse.status()),
                () -> assertEquals(account.getId().getValue(), accountCreationResponse.id())
        );
    }

    @Test
    void getAccountTest() {
        // given
        com.hexagonal.server.shared.kernel.common.valueobjects.Id accountId1 = TransactionId.ACCOUNT_ID_1;
        Account account = generateAccount();
        given(accountDomainService.getAccount(any(GetAccountOperation.class)))
                .willReturn(account);
        // when
        AccountResponse accountResponse = accountUsecase.getAccount(accountId1.getValue());
        // then
        verify(accountDomainService, times(1))
                .getAccount(getAccountOperationCaptor.capture());
        GetAccountOperation getAccountOperation = getAccountOperationCaptor.getValue();
        AccountDto accountDto = accountResponse.accountDto();
        assertAll(
                () -> assertEquals(accountId1, getAccountOperation.id()),
                () -> assertEquals(Name.ACCOUNT_NAME_1.getFirstName(), accountDto.firstname()),
                () -> assertEquals(Name.ACCOUNT_NAME_1.getLastName(), accountDto.lastname()),
                () -> assertEquals(Money.zero().getValue(), accountDto.balance()),
                () -> assertEquals(account.getCreatedAt().getTime(), accountDto.createdAt()),
                () -> assertEquals(account.getUpdatedAt().getTime(), accountDto.updatedAt())
        );
    }

    @Test
    void increaseBalanceTest() {
        // given
        com.hexagonal.server.shared.kernel.common.valueobjects.Id accountId1 = TransactionId.ACCOUNT_ID_1;
        Money money = Money.of(BigDecimal.TEN);
        // when
        accountUsecase.increaseBalance(accountId1.getValue(), money.getValue());
        // then
        verify(accountDomainService, times(1))
                .increaseBalance(increaseBalanceOperationCaptor.capture());
        IncreaseBalanceOperation increaseBalanceOperation = increaseBalanceOperationCaptor.getValue();
        assertAll(
                () -> assertEquals(accountId1, increaseBalanceOperation.id()),
                () -> assertEquals(money, increaseBalanceOperation.amount())
        );
    }

    @Test
    void decreaseBalanceTest() {
        // given
        com.hexagonal.server.shared.kernel.common.valueobjects.Id accountId1 = TransactionId.ACCOUNT_ID_1;
        Money money = Money.of(BigDecimal.TEN);
        // when
        accountUsecase.decreaseBalance(accountId1.getValue(), money.getValue());
        // then
        verify(accountDomainService, times(1))
                .decreaseBalance(decreaseBalanceOperationCaptor.capture());
        DecreaseBalanceOperation decreaseBalanceOperation = decreaseBalanceOperationCaptor.getValue();
        assertAll(
                () -> assertEquals(accountId1, decreaseBalanceOperation.id()),
                () -> assertEquals(money, decreaseBalanceOperation.amount())
        );
    }

}
