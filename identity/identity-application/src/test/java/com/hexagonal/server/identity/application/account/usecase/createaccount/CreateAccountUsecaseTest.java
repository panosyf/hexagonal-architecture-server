package com.hexagonal.server.identity.application.account.usecase.createaccount;

import com.hexagonal.server.identity.application.account.common.constant.Email;
import com.hexagonal.server.identity.application.account.common.constant.Name;
import com.hexagonal.server.identity.application.account.common.constant.Password;
import com.hexagonal.server.identity.application.account.common.constant.Username;
import com.hexagonal.server.identity.application.account.converter.request.AccountCreateRequestToOperation;
import com.hexagonal.server.identity.application.account.converter.response.AccountToDto;
import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.application.account.usecase.createaccount.CreateAccountUsecase;
import com.hexagonal.server.identity.application.account.usecase.createaccount.CreateAccountUsecaseImpl;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.core.convert.support.GenericConversionService;

import static com.hexagonal.server.identity.application.account.common.mock.AccountCreateRequestMock.generateAccountCreateRequest;
import static com.hexagonal.server.identity.application.account.common.mock.AccountMock.generateAccount;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CreateAccountUsecaseTest {

    private final AccountDomainService accountDomainService = mock(AccountDomainService.class);
    private final AccountRepositoryPort accountRepositoryPort = mock(AccountRepositoryPort.class);
    private final GenericConversionService genericConversionService = new GenericConversionService();
    private CreateAccountUsecase createAccountUsecase;
    private final ArgumentCaptor<CreateAccountOperation> createAccountOperationCaptor = ArgumentCaptor.forClass(CreateAccountOperation.class);

    @BeforeEach
    void init() {
        genericConversionService.addConverter(new AccountToDto());
        genericConversionService.addConverter(new AccountCreateRequestToOperation());
        createAccountUsecase = new CreateAccountUsecaseImpl(accountDomainService, accountRepositoryPort, genericConversionService);
    }

    @Test
    void createAccountTest() {
        // given
        AccountCreateRequest accountCreateRequest = generateAccountCreateRequest();
        Account account = generateAccount();
        given(accountDomainService.createAccount(any(CreateAccountOperation.class)))
                .willReturn(account);
        // when
        AccountCreationResponse accountCreationResponse = createAccountUsecase.createAccount(accountCreateRequest);
        // then
        verify(accountDomainService, times(1))
                .createAccount(createAccountOperationCaptor.capture());
        CreateAccountOperation createAccountOperation = createAccountOperationCaptor.getValue();
        assertAll(
                () -> assertEquals(Email.EMAIL_1, createAccountOperation.email()),
                () -> assertEquals(Username.USERNAME_1, createAccountOperation.username()),
                () -> assertEquals(Password.PASSWORD_1, createAccountOperation.password()),
                () -> assertEquals(Name.ACCOUNT_NAME_1, createAccountOperation.name()),
                () -> assertEquals(account.getId().getValue(), accountCreationResponse.id())
        );
    }

}
