package com.hexagonal.server.identity.application.account.usecase;

import com.hexagonal.server.identity.application.account.logging.LogInfoMessage;
import com.hexagonal.server.identity.application.account.model.dto.AccountDto;
import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;

public class AccountUsecaseImpl implements AccountUsecase {

    private final Logger log = LoggerFactory.getLogger(AccountUsecaseImpl.class);

    private final AccountDomainService accountDomainService;
    private final AccountRepositoryPort accountRepositoryPort;
    private final ConversionService conversionService;

    public AccountUsecaseImpl(
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
        log.info(LogInfoMessage.LOG_ACCOUNT_CREATED_INFO, account.getEmail(), account.getUsername());
        return new AccountCreationResponse(account.getId().getValue());
    }

    @Override
    public AccountDto getAccount(String id) {
        Account account = accountRepositoryPort.findById(Id.valueOf(id));
        return conversionService.convert(account, AccountDto.class);
    }

}
