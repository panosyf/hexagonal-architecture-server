package com.hexagonal.server.identity.application.account.usecase.createaccount;

import com.hexagonal.server.identity.application.account.common.logging.AccountLogInfoMessage;
import com.hexagonal.server.identity.application.account.usecase.createaccount.mapper.CreateAccountMapper;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

public class CreateAccountUsecaseImpl implements CreateAccountUsecase {

    private final Logger log = LoggerFactory.getLogger(CreateAccountUsecaseImpl.class);

    private final AccountDomainService accountDomainService;
    private final AccountRepositoryPort accountRepositoryPort;

    public CreateAccountUsecaseImpl(
            @Qualifier("accountDomainService") AccountDomainService accountDomainService,
            AccountRepositoryPort accountRepositoryPort) {
        this.accountDomainService = accountDomainService;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public AccountCreationResponse createAccount(AccountCreateRequest accountCreateRequest) {
        CreateAccountOperation createAccountOperation = CreateAccountMapper.toCreateAccountOperation(accountCreateRequest);
        Account account = accountDomainService.createAccount(createAccountOperation);
        accountRepositoryPort.save(account);
        log.info(AccountLogInfoMessage.LOG_ACCOUNT_CREATED_INFO, account.getEmail(), account.getUsername());
        return new AccountCreationResponse(account.getId().getValue());
    }

}
