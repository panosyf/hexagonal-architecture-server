package com.hexagonal.server.identity.application.account.usecase.createaccount;

import com.hexagonal.server.identity.application.account.common.logging.AccountLogInfoMessage;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;

public class CreateAccountUsecaseImpl implements CreateAccountUsecase {

    private final Logger log = LoggerFactory.getLogger(CreateAccountUsecaseImpl.class);

    private final AccountDomainService accountDomainService;
    private final AccountRepositoryPort accountRepositoryPort;
    private final ConversionService conversionService;

    public CreateAccountUsecaseImpl(
            AccountDomainService accountDomainService,
            AccountRepositoryPort accountRepositoryPort,
            ConversionService conversionService) {
        this.accountDomainService = accountDomainService;
        this.accountRepositoryPort = accountRepositoryPort;
        this.conversionService = conversionService;
    }

    @Override
    public AccountCreationResponse createAccount(AccountCreateRequest accountCreateRequest) {
        CreateAccountOperation createAccountOperation = conversionService.convert(accountCreateRequest, CreateAccountOperation.class);
        Account account = accountDomainService.createAccount(createAccountOperation);
        accountRepositoryPort.save(account);
        log.info(AccountLogInfoMessage.LOG_ACCOUNT_CREATED_INFO, account.getEmail(), account.getUsername());
        return new AccountCreationResponse(account.getId().getValue());
    }

}
